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
  (let [b config/SPRITE_SHEET_CLIP_BOUNDARY
        c (get-context)
        r (* js/Math.PI (rand) 2)]
    (.translate c pixel-x pixel-y)
    (.rotate c r)
    (.drawImage c
                config/SPRITE_SHEET
                (* b bubble-state) (* b bubble-index)
                b b
                (/ b -2) (/ b -2)
                b b)
    (.rotate c (* -1 r))
    (.translate c (* pixel-x -1) (* pixel-y -1))))
