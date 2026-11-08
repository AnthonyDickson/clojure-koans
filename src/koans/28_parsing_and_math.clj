(ns koans.28-parsing-and-math
  (:require [koan-engine.core :refer :all]
            [clojure.math :as math]))

;; Added in Clojure 1.11

(meditations
  "Reading a number from a string no longer needs Java"
  (= __ (parse-long "42"))

  "Decimals, even in scientific notation, have a parser too"
  (= __ (parse-double "1.5e3"))

  "A string that is not a number is not an error, merely nothing"
  (= __ (parse-long "forty-two"))

  "Only the exact words are true or false"
  (= [true false __] (map parse-boolean ["true" "false" "yes"]))

  "Identity can be read as well as invented"
  (= __ (class (parse-uuid "4fe5d828-6444-11e8-8222-720007e40350")))

  "No two invented identities are alike"
  (= __ (= (random-uuid) (random-uuid)))

  "Distance from zero ignores direction"
  (= [7 1/2] [(abs __) (abs -1/2)])

  "Some doubles are not numbers at all"
  (= __ (NaN? (/ 0.0 0.0)))

  "And some go on forever"
  (= __ (infinite? (/ 1.0 0.0)))

  "clojure.math wraps java.lang.Math, without the reflection"
  (= __ (math/floor 2.7))

  "Its functions are ordinary values, unlike Java's static methods once were"
  (= __ (map math/sqrt [4 9 16])))
