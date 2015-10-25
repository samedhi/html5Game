(ns html5game.math
  (:require 
   [html5game.config :as config]))

(defn derive-hypoteneus [a b]
  (js/Math.sqrt (+ (js/Math.pow a 2) (js/Math.pow b 2))))

(defn distance-between-points [point-1 point-2]
  (let [x-line-segment (- (:x point-1) (:x point-2))
        y-line-segment (- (:y point-1) (:y point-2))]
    (derive-hypoteneus x-line-segment y-line-segment)))

(defn angle-between-points [point-1 point-2]
  (let [x-diff (- (:x point-2) (:x point-1))
        y-diff (- (:y point-2) (:y point-1))
        a (js/Math.atan2 y-diff x-diff)]
    a))

(defn bubble-position [point-1 point-2]
  (let [a (angle-between-points point-1 point-2)
        d (distance-between-points point-1 point-2)
        rate-x (js/Math.cos a)
        rate-y (js/Math.sin a)
        max-x (* rate-x d)
        max-y (* rate-y d)
        max-t (/ d config/BUBBLE_VELOCITY)]
    (fn [t]
      (let [t (-> t (max 0) (min max-t))]
        {:x (min (* rate-x config/BUBBLE_VELOCITY t) max-x)
         :y (min (* rate-y config/BUBBLE_VELOCITY t) max-y)}))))
