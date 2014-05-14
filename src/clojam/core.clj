(ns clojam.core
  (:use [clojure.java.io :only [reader writer]]
   			[clojam.cases :only [get-cases]]
        [clojam.utils :only [string-to-int-seq]]))


(defn process-data [infile solver labels]
  (map solver (->> infile line-seq next (get-cases labels))))

(defn writeln [data wrtr]
  (.write wrtr data)
  (.newLine wrtr))

(defn gen-output [solved-cases out-format wrtr]
  (let [i (atom 0)]
    (doseq [case solved-cases]
      (swap! i inc)
      (-> case (out-format @i) (writeln wrtr)))))

(defn jam [infile case-format solver out-format outfile]
  (with-open [input (reader infile)
              wrtr (writer outfile)]
		(-> 
     	input 
      (process-data solver case-format) 
      (gen-output out-format wrtr))))