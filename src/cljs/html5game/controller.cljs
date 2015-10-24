(ns html5game.controller
  (:require 
   [html5game.config :refer [CONSOLE_MESSAGES?]]
   [html5game.model :refer [STATE]]))

(declare dispatch-fx)

(defn render
  "gets 'mature' messages in :enqueued, post them to & removes them from state"
  [{:keys [enqueued] :as state} {:keys [time] :as msg}]
  (let [[to-dispatch new-enqueued] (split-with #(<= (:run-after %) time) enqueued)]
    (reduce
     (fn [state {:keys [msg]}] (dispatch-fx state msg))
     (assoc state :enqueued (vec new-enqueued))
     to-dispatch)))

(defn dispatcher
  "`state + `msg => `new_state"
  [state {:keys [action] :as msg}]
  (condp = action
    :start-game (assoc state :state :playing)
    :enqueue (update-in state [:enqueued] #(->> (conj % msg) (sort-by :run-after) vec))
    :render (render state msg)
    nil))

(defn dispatch-fx
  "applies 'msg to the 'state of the world and returns a new state of the world"
  [state msg]
  (if (and (-> msg :action (= :render)) (-> state :enqueued count zero?))
    state
    (let [new-state (dispatcher state msg)]
      (when (and CONSOLE_MESSAGES? (not= new-state state))
        (if (nil? new-state)
          (js/console.warn (str "Unrecognized message: " (pr-str msg)))
          (js/console.log (str "message <= " (pr-str msg)))))
      (or new-state state))))

(defn dispatch
  "dispatch `msg on current app state"
  [msg]
  (swap! STATE dispatch-fx msg))
