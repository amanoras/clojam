(ns code-jam.2008.b
  (:gen-class :main true)
  (:use [clojam core cases utils])
  (:require [clojure.string :as string]
            [clojure.set :as set]))

; Problem B

(defn init-solution [size]
  (into (sorted-map) (zipmap (range 1 (inc size)) (repeat size 0))))

(defn get-shakes [shakes]
  (map #(partition 2 (rest (string-to-int-seq %)))
    (set shakes)))

(defn sort-shakes [shakes]
  (sort-by count 
    (map #(sort-by (juxt last first) %) shakes)))

(defn filter-shakes [shake customers]
  (let [flavor (first shake)
        malted (last shake)]
    (map 
       #(filter 
         (fn [x] 
           (or 
             (not= (first x) flavor) 
             (and 
               (= (first x) flavor) 
               (= (second x) malted)))) %) customers)))

(defn check-shakes [mix likes]
  (let [mix-set (set (partition 2 (interleave (keys mix) (vals mix))))
        likes-set (set likes)]
    (if (empty? (set/intersection mix-set likes-set))
      (loop [shakes likes]
        (if (empty? shakes)
          false
          (let [shake (first (first shakes)) 
                malted (second (first shakes))]
            (if-not (contains? mix shake)
              (assoc mix shake malted)
              (recur (rest shakes))))))
      mix)))

(defn process-customers [c]
  (loop [custs c 
         mix {}]
    (if mix
      (if (empty? custs)
        mix
        (let [new-mix (check-shakes mix (first custs))]
          (if new-mix
            (let [shake (first (set/difference (set (keys new-mix)) (set (keys mix))))
                  flavor [shake (get new-mix shake)]]
              (recur (sort-shakes (filter-shakes flavor (rest custs))) new-mix))
            (recur (rest custs) new-mix))))
      false)))

(defn solve-case [case]
  (let [mix (process-customers (sort-shakes (get-shakes (:t case))))]
    (if mix
      (string/join " " (vals (merge (init-solution (Integer/parseInt (:n case))) (into (sorted-map) mix))))
      "IMPOSSIBLE")))

(defn output-format [case i]
  (str "Case #" i ": " case))

(defn -main [infile outfile & args] 
  (jam infile [:n :m [:t]] 
     solve-case 
     output-format outfile))
