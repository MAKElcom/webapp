(ns webapp.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec {:dbtype "h2" :dbname "./webapp"})

(defn add-location-to-db
  [x y]
  (let [results (jdbc/insert! db-spec :locations {:x x :y y})]
    (assert (= (count results) 1))
    (first (vals (first results)))))

(defn get-xy
  [loc-id]
  (let [results (jdbc/query db-spec
                            ["select x, y from locations where id = ?" loc-id])]
    (assert (= (count results) 1))
    (first results)))


(defn add-paste-to-db
  [content]
  (let [results (jdbc/insert! db-spec :pastes {:content content})]
    (assert (= (count results) 1))
    (first (vals (first results)))))


(defn get-paste
  [paste-id]
  (let [results (jdbc/query db-spec
                            ["SELECT content FROM pastes WHERE id = ?" paste-id])]
    (assert (= (count results) 1))
    (first results)))

(defn get-all-pastes
  []
  (jdbc/query db-spec "SELECT id, content FROM pastes"))


(defn get-all-locations
  []
  (jdbc/query db-spec "select id, x, y from locations"))