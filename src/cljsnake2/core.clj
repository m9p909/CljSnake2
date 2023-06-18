(ns cljsnake2.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :refer [router ring-handler]]
            [cljsnake2.home :refer [home-routes]]
            [muuntaja.middleware :as middleware]
            [ring.logger :as logger]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn printnpass [v]
  (println v)
  v)

(defn print-body [handler]
  (fn [req]
    (printnpass (handler req))))

(def handler
  (middleware/wrap-format
   (print-body
    (logger/wrap-with-logger
     (ring-handler (router home-routes))))))

(defn start []
  (run-jetty
   handler
   {:port 3000 :join? false}))
