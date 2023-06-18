(ns cljsnake2.home
  (:require
   [ring.util.response :as res]
   [cljsnake2.core.api :as api]))

(defn start-game
  "receives a request with body example-post and returns a move (like example-move)"
  [req]
  (res/response (api/start (:body-params req))))

(defn move
  [req]
  (res/response (api/move (:body-params req))))

(defn end [req]
  (res/response (api/end (:body-params req))))

(def snake-data
  {:apiversion "1",
   :author "M9",
   :color "#888888",
   :head "default",
   :tail "default",
   :version "0.0.1-beta"})

(defn get-snake-data [req]
  (res/response snake-data))

(def home-routes
  [""
   ["/" {:get get-snake-data}]
   ["/start" {:post start-game}]
   ["/move" {:post move}]
   ["/end" {:post end}]])

