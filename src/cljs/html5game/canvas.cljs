(ns html5game.canvas
  (:require 
   [html5game.config :as config]))

(defn get-canvas []
  (js/document.getElementById "game"))

(defn get-context 
  ([] (get-context nil))
  ([canvas]
   (.getContext (or canvas (get-canvas)) "2d")))

(defn clear! []
  (let [canvas (get-canvas)
        context (get-context canvas)]
      (.clearRect context 0 0 (.-width canvas) (.-height canvas))))

(defn draw-bubble! [bubble-index bubble-state pixel-x pixel-y]
  (let [b config/SPRITE_SHEET_CLIP_BOUNDARY]
    (.drawImage (get-context)
                config/SPRITE_SHEET
                (* b bubble-index) (* b bubble-state)
                b b
                pixel-x pixel-y
                b b)))
