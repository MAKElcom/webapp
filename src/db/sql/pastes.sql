-- :name create-pastes-table :! :raw
-- :doc Create the pastes table
CREATE TABLE pastes (
    id bigint primary key auto_increment,
    content varchar(2048)
);


-- :name insert-new-paste :i! :n
-- :doc Insert a new paste
INSERT INTO pastes (content)
    VALUES (:content);

-- :name get-paste :? :*
-- :doc Gets the paste given an id
SELECT content FROM pastes WHERE id = :id;


-- :name get-all-pastes :? :*
-- :doc Get all of the pastes in the pastes table
SELECT id, content FROM pastes;