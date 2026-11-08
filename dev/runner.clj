(ns runner
  "Runs the koan engine from the Clojure CLI.

  `koan-engine.runner/exec` reads its settings and the required Clojure version
  from a Leiningen `project.clj`. This project uses `deps.edn` instead, so it
  hands the engine's tasks the same default settings directly."
  (:require [koan-engine.checker :as checker]
            [koan-engine.freshness :as freshness]
            [koan-engine.random :as random]))

(def koan-map
  {:koan-root "src/koans"
   :dojo-resource "dojo.clj"
   :koan-resource "koans.clj"})

(defn -main [& [task]]
  (case task
    "run" (freshness/setup-freshener koan-map)
    "test" (do (checker/test koan-map) (shutdown-agents))
    "random" (random/runner koan-map)
    (do (println "Usage: clojure -M:koans run|test|random")
        (System/exit 1))))
