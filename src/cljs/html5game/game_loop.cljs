(ns html5game.game-loop
  (:require
   [html5game.controller :as controller]
   goog.async.AnimationDelay))

(defn on-next-frame [fx]
  (.start (goog.async.AnimationDelay. fx)))

(defn render-message [t]
  (on-next-frame render-message)
  (controller/dispatch {:action :render :time t}))

(on-next-frame render-message)
