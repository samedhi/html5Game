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

;; http://www.gamasutra.com/view/feature/131424/pool_hall_lessons_fast_accurate_.php
;; http://paulbourke.net/geometry/

(defn collisions [bubbles {:keys [x y] :as fired-bubble} angle]
  (let [[start-left start-top] [x y]
        dx (js/Math.sin angle)
        dy (js/Math.sin angle)
        wiggle-factor (* config/BUBBLE_DIAMETER 0.75)]
    (for [{:keys [x y] :as bubble} bubbles
          :let [x-distance (- start-left x)
                y-distance (- start-top y)
                t (+ (* dx x-distance) (* dy y-distance))
                ex (+ (* t dx -1) start-left)
                ey (+ (* t dy -1) start-top)
                dist-ec (derive-hypoteneus (- ex x) (- ey y))]
          :when (< dist-ec wiggle-factor)]
      (let [dt (js/Math.sqrt (- (js/Math.pow config/BUBBLE_DIAMETER 2)
                                (js/Math.pow dist-ec 2)))]
        (->> [{:v (- t dt) :p 1} {:v (+ t dt) :p -1}]
             (map (fn [{:keys [v p]}]
                    (let [x-offset (* v dx)
                          y-offset (* v dy -1)
                          distance (derive-hypoteneus x-offset y-offset)
                          x (+ (* x-offset p) start-left)
                          y (+ y-offset start-top)]
                      {:x-offset x-offset :y-offset y-offset
                       :x x :y y
                       :distance distance
                       :bubble bubble})))
             vec
             (sort-by :distance)
             first)))))
