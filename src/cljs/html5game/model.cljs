(ns html5game.model)

;; Bubble is a {:x int :y int :additional-keys :state-values}

(defonce STATE (atom {:state :loading
                      :bubbles {} ;; [x y] : Bubble
                      :fired-bubble {} ;; a Bubble
                      }))

(js/console.log (pr-str @STATE))

(add-watch
 STATE
 :state-change
 (fn [_ _ o n] (when (not= o n) (js/console.log (str "state => " (pr-str n))))))

(defn new-fired-bubble [state]
  (let [b {:x 500 :y 550 :state (-> js/Math.random (* 4) js/Math.floor)}]
    (assoc state :fired-bubble b)))

(defn add-to-bubbles [state {:keys [x y] :as bubble}]
  (assoc state [x y] bubble))
