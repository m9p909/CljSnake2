(ns cljsnake2.core.randomalgo)

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

(defn get-my-head [board]
  (:head (first (:snakes board))))

(defn remove-out-of-bounds-moves [height width head moves]
  "takes (int int point move) and returns not out of bounds moves"
  (filter #(not (is-out-of-bounds
                 height width
                 (apply-move head %)))
          moves))

(defn get-points-occupied-by-snakes [snakes]
  (set (flatten (map #(:body %) snakes))))

(defn remove-snake-collision-moves
  [head snakes moves]
  (let [snake-points
        (get-points-occupied-by-snakes snakes)]
    (filter
     #(not (contains? snake-points (apply-move head %)))
     moves)))

(= (set (remove-snake-collision-moves
         {:x 1 :y 2}
         '({:body [{:x 1 :y 1} {:x 2 :y 2}]}
           {:body [{:x 2 :y 3} {:x 4 :y 4}]})
         moves)) #{:up :left})

(defn valid-moves [req]
  (let [board (:board req)]
    (->> moves
         (remove-out-of-bounds-moves
          (:height board)
          (:width board)
          (get-my-head board))
         (remove-snake-collision-moves
          (get-my-head board)
          (:snakes board)))))

(defn rand-move  [req]
  (let [moves (valid-moves req)]
    (if (> (count moves) 0)
      {:move
       (rand-nth (vec (valid-moves req)))}
      {:move
       :up
       :shout
       "FUCK"})))




