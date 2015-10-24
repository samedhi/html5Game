(ns html5game.future
  (:refer-clojure :exclude [delay])
  (:require
   [html5game.controller :as controller]))

(defn schedule
  "`msg will be dispatched at a time after `time milliseconds (Epoch Time)"
  [msg time]
  (controller/dispatch {:action :enqueue :run-after time :msg msg}))

(defn delay
  "`msg will be dispatched `time milliseconds in the future"
  ([msg]
   (delay msg 0))
  ([msg ms]
   (let [current (js/Date.)]
     (schedule msg (+ (.getTime current) ms)))))
