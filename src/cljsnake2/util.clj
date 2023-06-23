(ns cljsnake2.util
  (:require
   [muuntaja.core :as m]))

(defn tojson [data]
  (->> (m/encode "application/json" data) slurp))
