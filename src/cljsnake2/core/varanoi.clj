(ns cljsnake2.core.varanoi
  (:require [cljsnake2.core.general :as g]))

(defn vec2d
  "Return an x by y vector with all entries equal to val."
  [x y val]
  (vec (repeat y (vec (repeat x val)))))

(defn get2d
  [grid y x]
  (-> grid (get y) (get x)))

(defn set2d
  [grid y x v]
  (assoc-in grid [y x] v))

(defn get-bad-squares [req]
  (let [you (:you req)
        id (-> req :you :id)
        yourbody (drop-last (:body you))
        snakes (->> req :board :snakes (filter #(not (= (:id %) id))))
        bodies (g/get-points-occupied-by-snakes snakes)
        bodies2 (into bodies yourbody)]
    bodies2))

(defn get-distance-grid [req move]
  (let [myhead (g/apply-move (g/get-my-head req) move)
        board (:board req)
        grid (vec2d (:width board) (:height board) 0)
        bad-squares (get-bad-squares req)]
    (loop [n 1
           moveid 0
           g grid
           loc myhead]
      (cond
        (> moveid 3) (recur (inc n) 0 g myhead)
        :else
        (let [value (g/apply-move loc (get g/moves moveid))]
          (if (and (not (contains? bad-squares value)) (= 0 (get2d grid (:y value) (:x value))))
            (recur n (inc moveid) (set2d g (:y value) (:x value) n) value)
            (recur n (inc moveid) g loc)))))))

(defn score [req move]
  "scores a move from 0 to 1 based
  on how many squares the snake can get to first")



