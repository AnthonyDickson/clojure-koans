(ns koans.33-modern-java-interop
  (:require [koan-engine.core :refer :all]))

;; Added in Clojure 1.12

(meditations
  "Instance methods, qualified by their class, are functions too"
  (= __ (map String/.toUpperCase ["select" "from" "where"]))

  "As are static methods, with no wrapping fn needed"
  (= [1 2 3] (map __ ["1" "2" "3"]))

  "Constructors are reached through new"
  (= __ (map (comp str StringBuilder/new) ["hello" "world"]))

  "When a method is overloaded, param tags choose the signature"
  (= __ (^[long int] Long/toString 255 16))

  "Even when the method is used as a value"
  (= __ (map ^[long] Long/toString [10 20]))

  "Array classes have a name of their own: the number is the dimension"
  (= __ (class (into-array String ["a" "b"])))

  "Primitive arrays too"
  (= __ (class (make-array Long/TYPE 2 2)))

  "Clojure functions can be passed where Java expects a functional interface"
  (= __ (let [numbers (java.util.ArrayList. [1 2 3 4 5])]
          (.removeIf numbers even?)
          (vec numbers)))

  "Anything you can deref can supply a value to Java"
  (= __ (java.util.Objects/requireNonNullElseGet nil (delay "fallback"))))
