-- :name create-locations-table :! :raw
-- :doc Create the locations table
CREATE TABLE locations (
    id bigint primary key auto_increment,
    x integer,
    y integer
);

-- :name get-coord :? :*
-- :doc Gets the location given an id
SELECT x, y FROM locations WHERE id = :id;


-- :name get-all-coords :? :*
-- :doc Gets all locations in the locations table
SELECT id, x, y FROM locations;

-- :name insert-new-location :! :n
-- :doc Insert a new location by x, y and return the number of rows affected
INSERT INTO locations (x, y)
    VALUES (:x, :y);


-- :name drop-locations-table :!
-- :doc Drop the locations table if it exists
DROP TABLE if EXISTS locations;