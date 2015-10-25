(ns html5game.sound
  (:require
   [html5game.config :as config])
  (:refer-clojure :exclude [pop!]))

(defn pop! []
  (.play (js/Audio. config/POP_SRC)))
