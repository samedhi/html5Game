(ns html5game.boot-build
  (:require [boot.core :as core :refer [deftask]]
            [boot.task.built-in :as task]
            [adzerk.boot-reload    :refer [reload]]
            [adzerk.boot-cljs      :refer [cljs]]
            [adzerk.boot-cljs-repl :refer [cljs-repl]]
            [pandeiro.boot-http    :refer [serve]]))

(deftask dev
  "Build project for local extension development"
  []
  (comp
   (serve)
   (task/watch)
   (task/speak)
   (reload)
   (cljs-repl)
   (cljs)))

(deftask prod
  "Build project to be packaged for Google Play Store"
  []
  (comp
   (cljs :optimizations :advanced)))
