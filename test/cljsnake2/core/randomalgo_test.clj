(ns cljsnake2.core.randomalgo-test
  (:require
   [clojure.test :refer [deftest is testing] :as test]
   [cljsnake2.core.randomalgo :refer [rand-move]]))

(def data
  (read-string
   (slurp "./test/cljsnake2/core/cases.edn")))

(defn run-case [test-case]
  (testing (:name test-case)
    (is
     (contains?
      (set (map name (:exp test-case)))
      (name (:move (rand-move (:req test-case))))))))

(deftest randomalgo-cases
  (for [test-case data]
    (run-case test-case)))





