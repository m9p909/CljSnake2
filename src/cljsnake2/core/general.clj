(ns cljsnake2.core.general
  (:require [clojure.tools.logging :refer [info]]))

(defn get-my-head [req]
  (:head (:you req)))

(def moves '(:up :right :down :left))

(def moves-map {:up {:x 0 :y 1}
                :right {:x 1 :y 0}
                :down {:x 0 :y -1}
                :left {:x -1 :y 0}})

(defn +point [& points]
  (reduce
   (fn [prev curr]
     {:x (+ (:x prev) (:x curr))
      :y (+ (:y prev) (:y curr))})
   {:x 0 :y 0}
   points))

(defn apply-move [point move]
  (let [transform (get moves-map move)]
    (+point transform point)))

(defn is-out-of-bounds [height width point]
  (or (> (:x point) (dec width))
      (< (:x point) 0)
      (> (:y point) (dec height))
      (< (:y point) 0)))
(is-out-of-bounds 11 11 {:x 11 :y 11})

(defn remove-out-of-bounds-moves [req moves]
  "takes (int int point move) and returns not out of bounds moves"
  (let [board (:board req)
        height (:height board)
        width (:width board)
        head (get-my-head req)]
    (filter #(not (is-out-of-bounds
                   height width
                   (apply-move head %)))
            moves)))

(defn get-points-occupied-by-snakes [snakes]
  (set (flatten (map #(:body %) snakes))))

(defn remove-snake-collision-moves
  [req moves]

  (let [board (:board req)
        snakes (:snakes board)
        head (get-my-head req)
        snake-points
        (get-points-occupied-by-snakes snakes)]
    (filter
     #(not
       (contains? snake-points (apply-move head %)))
     moves)))

(defn printnpass [var]
  (info (prn-str var)
        var))

(defn valid-moves [req]
  (->> moves
       (remove-out-of-bounds-moves req)
       (remove-snake-collision-moves req)))

