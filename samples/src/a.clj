(ns code-jam.2008.a
  (:gen-class :main true)
  (:use [clojam core cases utils])
  (:require [clojure.string :as string]))

; Problem A

(defn solve-case [case]
  (reduce + 
    (map * 
      (sort (string-to-int-seq (:v1 case)))
      (reverse (sort (string-to-int-seq (:v2 case)))))))

(defn output-format [case i]
  (str "Case #" i ": " case))

(defn -main [infile outfile & args] 
  (jam infile [:l :v1 :v2] 
     solve-case 
     output-format outfile))