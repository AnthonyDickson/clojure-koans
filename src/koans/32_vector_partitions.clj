(ns koans.32-vector-partitions
  (:require [koan-engine.core :refer :all]))

;; Added in Clojure 1.12

(meditations
  "partitionv is partition that gives back vectors"
  (= __ (partitionv 2 [1 2 3 4 5]))

  "Which you can tell by asking"
  (= [__ __] [(vector? (first (partition 2 (range 4))))
              (vector? (first (partitionv 2 (range 4))))])

  "partitionv-all keeps the leftovers"
  (= __ (partitionv-all 2 [1 2 3 4 5]))

  "Steps and padding work just as they do with partition"
  (= __ (partitionv 2 3 [:x] (range 7)))

  "splitv-at cuts a collection in two, the first half a vector"
  (= __ (splitv-at 2 [:a :b :c :d])))
