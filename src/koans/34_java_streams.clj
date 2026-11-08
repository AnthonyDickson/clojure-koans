(ns koans.34-java-streams
  (:require [koan-engine.core :refer :all]))

;; Added in Clojure 1.12

(meditations
  "Java streams can become sequences"
  (= __ (stream-seq! (.stream [1 2 3])))

  "They can be reduced like any collection"
  (= __ (stream-reduce! + (.stream [1 2 3 4])))

  "Or poured into one, through a transducer if you like"
  (= __ (stream-into! #{} (map inc) (.stream [1 2 3])))

  "Or transduced"
  (= __ (stream-transduce! (filter even?) + (.stream (range 10))))

  "Java's own stream operations accept Clojure functions"
  (= __ (-> (.stream ["a" "bb" "ccc"])
            (.map count)
            (.filter odd?)
            .toList))

  "But a stream, once consumed, is gone"
  (= __ (let [stream (.stream [1 2 3])]
          (stream-reduce! + stream)
          (try (stream-reduce! + stream)
               (catch IllegalStateException _ :used-up)))))
