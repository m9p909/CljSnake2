(ns cljsnake2.core.api-test
  (:require
   [clojure.test :refer [deftest is testing] :as test]
   [cljsnake2.core.api :refer [start move end]]
   [cljsnake2.core.models :refer [explain-req]]
   [clj-yaml.core :as yaml]
   [muuntaja.core :as m]))

(def datayml
  (->> "./test/cljsnake2/core/cases.yml"
       slurp
       yaml/parse-string
       (m/encode "application/json")
       (m/decode "application/json")))

(defn printnpass [val] (println val) val)

(defn run-case [test-case]
  (testing (:name test-case)
    (is
     (contains?
      (set (map name (:exp test-case)))
      (name
       (:move
        (move (:req test-case))))))
    (is
     (not=
      (:shout (move (:req test-case)))
      "AAAA bad input"))))
(-> datayml (get 2) :req explain-req)

(deftest randomalgo-cases
  (for [test-case datayml]
    (run-case test-case)))





