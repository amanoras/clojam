(ns clojam.cases)

(defn group-lines [n input]
  (partition n n nil input))

(defn group-var-lines 
  ([labels inseq] 
    (lazy-seq (group-var-lines labels inseq [])))
  ([labels inseq cases]  
    (let [vals-count (dec (count labels))]
      (if (empty? inseq)
        cases
        (let [var-count (Integer/parseInt (last (take vals-count inseq)))
              read-count (+ vals-count var-count)]
          (lazy-seq
            (group-var-lines labels (drop read-count inseq) (conj cases (take read-count inseq)))))))))

(defn create-case [labels lines]
  (if (vector? (last labels))
    (let [vals-count (dec (count labels))
          case (create-case (take vals-count labels) (take vals-count lines))
          var-name (first (last labels))]
      (assoc case var-name (drop vals-count lines)))
    (zipmap labels lines)))

(defn get-cases [labels input]
  (map #(create-case labels %) 
    (if (vector? (last labels))
      (group-var-lines labels input)
      (group-lines (count labels) input))))

(defn solve-cases [solve cases]
  (map solve cases))
