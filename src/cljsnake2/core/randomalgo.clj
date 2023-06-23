(ns cljsnake2.core.randomalgo
  (:require [clojure.tools.logging :refer [info]]
            [cljsnake2.core.general :refer [get-my-head]])))




(defn rand-move  [req]
  (let [moves (valid-moves req)]
    (if (> (count moves) 0)
      {:move
       (rand-nth (vec (valid-moves req)))}
      {:move
       :up
       :shout
       "FUCK"})))




