(ns cljsnake2.core.api
  (:require
   [cljsnake2.core.models :refer [validate-req]]
   [cljsnake2.core.randomalgo :refer [rand-move]]))

(defn start [req])

(defn- handle-bad-req [req]
  (println "bad input")
  (pr req)
  {:move :up :shout "AAAA bad input"})

(defn move  [req]
  (println req)
  (if (validate-req req)
    (rand-move req)
    (handle-bad-req req)))
(defn end [req])


