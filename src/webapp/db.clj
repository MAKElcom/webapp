(ns webapp.db
  (:require [clojure.java.jdbc :as jdbc]
            [db.locations :as locations]))

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
  (let [results (jdbc/insert! db :pastes {:content content})]
    (assert (= (count results) 1))
    (first (vals (first results)))))


(defn get-paste
  [paste-id]
  (let [results (jdbc/query db
                            ["SELECT content FROM pastes WHERE id = ?" paste-id])]
    (assert (= (count results) 1))
    (first results)))

(defn get-all-pastes
  []
  (jdbc/query db "SELECT id, content FROM pastes"))


(defn get-all-locations
  []
  (locations/get-all-coords db))