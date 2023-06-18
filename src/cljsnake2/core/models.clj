(ns cljsnake2.core.models
  (:require [clojure.spec.alpha :as s]))

(s/def :core/id string?)
(s/def :core/health int?)
(s/def :core/x int?)
(s/def :core/y int?)
(s/def :core/point (s/keys :req-un [:core/x :core/y]))
(s/def :core/body
  (s/coll-of :core/point :kind vector? :distinct true))
(s/def :core/head :core/point)
(s/def :core/snake (s/keys  :req-un [:core/id
                                     :core/head
                                     :core/health
                                     :core/body]))
(s/def :core/height int?)
(s/def :core/width int?)
(s/def :core/turn int?)
(s/def :core/food (s/coll-of :core/point :kind vector? :distinct true))
(s/def :core/hazards (s/coll-of :core/point :kind vector? :distinct true))
(s/def :core/snakes (s/coll-of :core/snake :kind vector? :distinct true))
(s/def :core/board (s/keys :req-un
                           [:core/height
                            :core/width
                            :core/hazards
                            :core/snakes
                            :core/food]))

(s/def :ruleset/name string?)
(s/def :ruleset/version string?)
(s/def :ruleset/ruleset (s/keys :req-un [:ruleset/name :ruleset/version]))
(s/def :ruleset/map string?)
(s/def :ruleset/timeout int?)
(s/def :ruleset/source "league")
(s/def :core/game (s/keys :req-un [:core/id
                                   :ruleset/ruleset
                                   :ruleset/map
                                   :ruleset/timeout
                                   :rulset/source]))

(s/def :core/you :core/snake)

(s/def :core/request (s/keys :req-un [:core/game
                                      :core/turn
                                      :core/board
                                      :core/you]))

(defn validate-req [post]
  (s/valid? :core/request post))

(s/def :core/move  #{:up :down :left :right})
(s/def :core/shout string?)
(s/def :core/res (s/keys :req-un [:core/move]
                         :opt-un [:core/shout]))

(defn validate-res [res]
  (s/valid? :core/res res))

(defn valid-snake [snake]
  (s/valid? :core/snake snake))


