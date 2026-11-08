(ns koans.31-iteration
  (:require [koan-engine.core :refer :all]))

;; Added in Clojure 1.11

(def api-pages
  {0 {:items [:a :b] :next-page 1}
   1 {:items [:c :d] :next-page 2}
   2 {:items [:e]}})

(defn fetch-page
  "Pretend this is a slow call to a paginated web API."
  [page-number]
  (get api-pages page-number))

(meditations
  "Iteration calls a step function, starting from an initial key"
  (= __ (first (iteration fetch-page :initk 0)))

  "Without knowing how to continue, it stops after one step"
  (= __ (count (vec (iteration fetch-page :initk 0))))

  "The key function finds the key for the next step, until it finds nil"
  (= __ (count (vec (iteration fetch-page :initk 0 :kf :next-page))))

  "The value function picks what to keep from each step"
  (= __ (vec (iteration fetch-page :initk 0 :kf :next-page :vf :items)))

  "Pages of items flatten nicely into one collection"
  (= [:a :b :c :d :e]
     (into [] __ (iteration fetch-page :initk 0 :kf :next-page :vf :items)))

  "Steps are only taken when their values are needed"
  (= __ (let [calls (atom 0)
              pages (iteration (fn [page-number]
                                 (swap! calls inc)
                                 (fetch-page page-number))
                               :initk 0
                               :kf :next-page)]
          [(:items (first pages)) @calls])))
