(ns clojam.utils
  (:require [clojure.string :as string]))

(defn split-on-space [s]
  (string/split s #"\s"))

(defn to-int-seq [items]
  (map 
    #(Integer/parseInt %) items))

(defn to-float-seq [items]
  (map 
    #(Float/parseFloat %) items))

(defn string-to-float-seq [s]
  (to-float-seq (split-on-space s)))

(defn string-to-int-seq [s]
  (to-int-seq (split-on-space s)))

(defn in? [inseq x]
  (some #(= x %) inseq))

(defn combinations [vals n]
  (loop [combs [[]]
         i 0]
    (if (= i n)
      combs
      (recur 
        (for [c combs
              b vals]
          (cons b c)) (inc i)))))