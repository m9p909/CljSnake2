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

(defn build-snake-grid [req]
  ;; todo
  )
(defn score [req move]
  "scores a move from 0 to 1 based
  on how many squares the snake can get to first"
  (let [myhead (g/get-my-head req)
        board (:board req)
        grid (vec2d (:width board) (:height board) :blank)]
    (loop [g grid
           x (:x head)
           y (:y head)
           num 30]
      (if (not (int? (get2d g y x)))
        (let [newg (set2d g y x num)
              newnum (dec num)]
          (for [dir g/moves]))))))



