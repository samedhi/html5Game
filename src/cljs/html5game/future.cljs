(ns html5game.future
  (:refer-clojure :exclude [delay])
  (:require
   [html5game.state :as state]))

(defn schedule
  "`msg will be dispatched at a time after `time milliseconds (Epoch Time)"
  [msg time]
  (state/dispatch {:action :enqueue :run-after time :msg msg}))

(defn delay
  "`msg will be dispatched `time milliseconds in the future"
  ([msg]
   (delay msg 0))
  ([msg time]
   (let [current (js/Date.)]
     (schedule msg (+ (.getTime current) time)))))
