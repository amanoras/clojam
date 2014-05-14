(ns clojam.cases-test
  (:use [midje.sweet])
  (:use [clojam.cases]))


(fact
  (get-cases [:a :b] [1 2 3 4 5 6]) => [{:a 1 :b 2} {:a 3 :b 4} {:a 5 :b 6}])

(fact "Partitioning by a variable length returns a sequence"
  (seq? (group-var-lines [:a :b [:c]] [1 2 3 4 2 3 4 5 6])) => true)

(fact "A sequence can be partitioned by a variable length"
  (group-var-lines [:a :b [:c]] [1 2 3 4 2 3 4 5 6]) => '((1 2 3 4) (2 3 4 5 6)))

; Test case generation
(fact 
  (get-cases [:a :b :c] [1 2 3 4 5 6]) => '({:a 1 :b 2 :c 3} {:a 4 :b 5 :c 6}))

(fact 
  (get-cases [:a :b [:c]] [1 2 3 4 2 3 4 5 6]) => '({:a 1 :b 2 :c [3 4]} {:a 2 :b 3 :c [4 5 6]}))