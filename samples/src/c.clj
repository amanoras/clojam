(ns code-jam.2008.c
  (:gen-class :main true)
  (:use [clojam core cases utils])
  (:require [clojure.string :as string])
  (:import [java.math MathContext]))

; Problem C

(def base-val 5.2360679774997896964091736687313)

(defn expt [in-val n precision]
  (let [mod-val (Math/pow 10 precision)]
    (loop [val in-val i 1]
      (if (= i n)
        val
        (recur (mod (* val in-val) mod-val) (inc i))))))

(defn op [n]
  (mod (int (expt base-val n 3)) 1000))

(defn solve-case [case]
  (format "%03d" (op (Integer/parseInt (:n case)))))

(defn output-format [case i]
  (str "Case #" i ": " case))

(defn -main [infile outfile & args] 
  (jam infile [:n] 
     solve-case 
     output-format outfile))