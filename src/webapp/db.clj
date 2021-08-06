(ns webapp.db
  (:require [clojure.java.jdbc :as jdbc]
            [db.locations :as locations]
            [db.pastes :as pastes]))

(def db {:dbtype "h2" :dbname "./webapp"})

(defn add-location-to-db
  [x y]
  (let [results (locations/insert-new-location db {:x x :y y})]
    (assert (= results 1))
    results))

(defn get-xy
  [loc-id]
  (let [results (locations/get-coord db {:id loc-id})]
    (assert (= (count results) 1))
    (first results)))


(defn add-paste-to-db
  [content]
  (let [results (pastes/insert-new-paste db {:content content})]
    (assert (= results 1))
    results))


(defn get-paste
  [paste-id]
  (let [results (pastes/get-paste db {:id paste-id})]
    (assert (= (count results) 1))
    (first results)))

(defn get-all-pastes
  []
  (pastes/get-all-pastes db))


(defn get-all-locations
  []
  (locations/get-all-coords db))