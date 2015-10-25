(ns html5game.config
  (:require
   [html5game.model :refer [STATE]]))

(enable-console-print!)

(def CONSOLE_MESSAGES? true)

(def BUBBLE_DIAMETER 44)

(def SPRITE_SHEET 
  (let [image (js/Image.)]
    (set! (.-onload image) (fn [_] (swap! STATE assoc :state :initial)))
    (set! (.-src image) "image/bubble_sprite_sheet.png")
    image))

(def SPRITE_SHEET_CLIP_BOUNDARY 50)

(def POP_SRC "sound/pop.mp3")
