(ns html5game.model)

(defonce STATE (atom {:state :loading}))

(js/console.log (pr-str @STATE))

(add-watch
 STATE
 :state-change
 (fn [_ _ o n] (when (not= o n) (js/console.log (str "state => " (pr-str n))))))
