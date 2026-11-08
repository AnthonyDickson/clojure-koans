(ns koans.30-keyword-arguments
  (:require [koan-engine.core :refer :all]
            ;; :as-alias gives a namespace a short name without loading it,
            ;; or even needing it to exist (Clojure 1.11)
            [koans.users :as-alias user]))

(defn greet [name & {:keys [greeting punctuation]
                     :or {greeting "Hello" punctuation "!"}}]
  (str greeting ", " name punctuation))

(meditations
  "Options can follow the required arguments as keywords and values"
  (= __ (greet "Rich" :greeting "Hi"))

  "Since Clojure 1.11 the same options can be passed as a map"
  (= "Hi, Rich?" (greet "Rich" __))

  "Or as both, so long as the map comes last"
  (= __ (greet "Rich" :greeting "Hey" {:punctuation "."}))

  "So a map of options built elsewhere no longer needs apply"
  (= __ (let [options {:greeting "Yo"}]
          (greet "Rich" options)))

  "An alias can qualify keywords for a namespace that is never loaded"
  (= __ ::user/name)

  "And keeps destructuring of qualified keys short"
  (= __ (let [{::user/keys [name]} {:koans.users/name "Ada"}]
          name)))
