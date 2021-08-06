(ns db.locations
  (:require [hugsql.core :as hugsql]))


(hugsql/def-db-fns "db/sql/locations.sql")