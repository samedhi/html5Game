(ns html5game.utility)

(defn mouse-coords [e]
  {:x (.-offsetX e) :y (.-offsetY e)})
