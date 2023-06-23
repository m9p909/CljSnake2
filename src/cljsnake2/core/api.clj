(ns cljsnake2.core.api
  (:require
   [cljsnake2.core.models :refer [validate-req validate-res]]
   [cljsnake2.core.randomalgo :refer [rand-move]]
   [clojure.core.async :refer [go]]
   [clojure.tools.logging :refer [info error]]
   [cljsnake2.util :refer [tojson]]
   [muuntaja.core :as m]))

(defn start [req])

(defn- handle-bad-req [req]
  (error "bad input")
  (error (tojson req))
  {:move :up :shout "AAAA bad input"})

(defn- handle-bad-res [req res]
  (error "bad output")
  (error (tojson res))
  (error "input")
  (error (tojson req))
  {:move :up :shout "AAAA bad output"})

(def req-map (atom {}))

(defn get-game-id [req]
  (str (:id req) (-> req :you :id)))

(defn save [req]
  (let [id (get-game-id req)]
    (swap! req-map (fn [prev]
                     (assoc prev (:data id) req)))))

(defn move  [req]
  (if (validate-req req)
    (do (go (save req))
        (let [move (rand-move req)]
          (if (validate-res move)
            move
            (handle-bad-res req move))))
    (handle-bad-req req)))

(defn end [req]
  (let [id (get-game-id req)]
    (info (str "last request for " id))
    (info (tojson (get @req-map id)))
    (swap! req-map #(dissoc % id))
    nil))


