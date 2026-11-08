(ns koans.29-updating-maps
  (:require [koan-engine.core :refer :all]))

;; Added in Clojure 1.11

(meditations
  "Every value in a map can be transformed at once"
  (= __ (update-vals {:a 1 :b 2} inc))

  "And every key"
  (= {"a" 1 "b" 2} (update-keys {:a 1 :b 2} ___))

  "Data from the outside world often arrives with string keys"
  (= {:name "Rich" :language "Clojure"}
     (update-keys {"name" "Rich" "language" "Clojure"} ___))

  "When transformed keys collide, only one survives"
  (= __ (count (update-keys {1 :a 2 :b 3 :c} odd?)))

  "Together they reshape a map from end to end"
  (= __ (-> {"apples" "3" "pears" "5"}
            (update-keys keyword)
            (update-vals parse-long))))
