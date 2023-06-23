(ns cljsnake2.core.models-test
  (:require
   [clojure.test :refer [deftest is testing] :as test]
   [cljsnake2.core.models :refer [validate-req valid-snake validate-res]]))

(def example-battlesnake-object
  {:shout "why are we shouting??",
   :squad "1",
   :name "Sneky McSnek Face",
   :head {:x 0, :y 0},
   :id "totally-unique-snake-id",
   :customizations {:color "#26CF04",
                    :head "smile",
                    :tail "bolt"},
   :latency "123",
   :length 3,
   :health 54,
   :body [{:x 0, :y 0} {:x 1, :y 0} {:x 2, :y 0}]})

(def bad-battlesnake-object
  {:shout "why are we shouting??",
   :squad "1",
   :name "Sneky McSnek Face",
   :head {:x 0, :y 0},
   :id "totally-unique-snake-id",
   :customizations {:color "#26CF04",
                    :head "smile",
                    :tail "bolt"},
   :latency "123",
   :length 3,
   :health 54,
   :body [{:y 0} {:x 1, :y 0} {:x 2, :y 0}]})

(def example-game-object
  {:id "totally-unique-game-id",
   :ruleset {:name "standard", :version "v1.2.3"},
   :map "standard",
   :timeout 500,
   :source "league"})

(def example-post-success
  {:game example-game-object
   :turn 0
   :board {:height 11,
           :width 11,
           :food [{:x 5, :y 5} {:x 9, :y 0} {:x 2, :y 6}],
           :hazards [{:x 0, :y 0} {:x 0, :y 1} {:x 0, :y 2}],
           :snakes [example-battlesnake-object]}
   :you example-battlesnake-object})

(def example-post-fail
  {:game example-game-object
   :turn 0
   :board {:height 11,
           :food [{:x 5, :y 5} {:x 9, :y 0} {:x 2, :y 6}],
           :hazards [{:x 0, :y 0} {:x 0, :y 1} {:x 0, :y 2}],
           :snakes [example-battlesnake-object]}
   :you example-battlesnake-object})
(def example-res
  {:shout "asjdklsa" :move :up})

(def bad-res
  {:shout "cool shout"})

(deftest validation-test
  (testing "validation works"
    (is (= true (validate-req example-post-success))))
  (testing "bad validation works"
    (is (= false (validate-req example-post-fail))))
  (testing "validate snake"
    (is (= true (valid-snake example-battlesnake-object))))
  (testing "validate bad snake"
    (is (= false (valid-snake bad-battlesnake-object))))
  (testing "validate response"
    (is (= true (validate-res example-res))))
  (testing "validate bad response"
    (is (= false (validate-res bad-res)))))

(test/run-all-tests)


