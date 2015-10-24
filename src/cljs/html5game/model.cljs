(ns html5game.model
  (:require 
   [html5game.config :refer [CONSOLE_MESSAGES?]]))

(defonce STATE (atom {:state :initial}))

(js/console.log (pr-str @STATE))

(add-watch
 STATE
 :state-change
 (fn [_ _ o n] (when (not= o n) (js/console.log (str "state => " (pr-str n))))))
