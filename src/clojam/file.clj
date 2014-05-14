(ns clojam.file
  (:use [clojure.java.io])
  (:require [clojure.string :as string]))

(defn read-file [filename func]
  (with-open [rdr (reader filename)]
    (func (line-seq rdr))))

(defn read-n-lines [lines n]
  ((fn [lines n out]
    (if (= n 1)
      (conj out (first lines))
      (recur (next lines) (dec n) (conj out (first lines))))) lines n []))

(defn out-format [i case data-format]
    (str "Case #" i ": " (data-format case)))

(defn write-file [data filename data-format]
  (with-open [wrtr (writer filename)]
    (doseq [[i case] (map list (range 1 (inc (count data))) data)]
      (.write wrtr (out-format i case data-format))
      (.newLine wrtr))))

