(ns db.pastes
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "db/sql/pastes.sql")
