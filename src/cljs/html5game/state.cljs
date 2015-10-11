(ns html5game.state)

(enable-console-print!)

;; The CONSTANTS
(def CONSOLE_MESSAGES? true)

;; The MODEL
(defonce STATE (atom {:state :initial}))

(js/console.log (pr-str @STATE))

(add-watch
 STATE
 :state-change
 (fn [_ _ o n] (when (not= o n) (js/console.log (str "state => " (pr-str n))))))

;; The CONTROLLER

(defn dispatcher [state {:keys [action] :as msg}]
  (condp = action
    :start-game (assoc state :state :playing)
    state))

(defn dispatch 
  "applies the 'msg to the 'state of the world and returns a new state"
  [state msg]
  (let [new-state (dispatcher state msg)]
    (if (and CONSOLE_MESSAGES? (= new-state state))
      (js/console.warn (str "I was unable\\ignored the message: " (pr-str msg)))
      (js/console.log (str "message <= " (pr-str msg))))
    new-state))

(defn state-dispatch [msg]
  (swap! STATE dispatch msg))
