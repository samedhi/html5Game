(set-env!
 :source-paths #{"src/clj" "src/cljs"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs "0.0-3308-0" ] ;; failed
                 [adzerk/boot-cljs-repl "0.2.0"] 
                 [adzerk/boot-reload "0.3.1"] ;; failed
                 [org.clojure/clojure "1.7.0-RC1"] ;; how?
                 [org.clojure/clojurescript "1.7.48"] 
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.9.0"]
                 [pandeiro/boot-http  "0.7.0-SNAPSHOT"]
                 [sablono "0.3.6"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer :all]
 '[adzerk.boot-reload    :refer [reload]]
 '[html5game.boot-build :refer :all])

(task-options!
 pom {:project 'facilely
      :version "0.1.0"}
 reload {:port 12345}
 cljs {:source-map true
       :optimizations :none
       :pretty-print true})
