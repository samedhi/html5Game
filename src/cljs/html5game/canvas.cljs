(ns html5game.canvas)

(defn get-context []
  (.getContext (js/document.getElementById "game_canvas") "2d"))


