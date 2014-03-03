(ns food_truck.test.test_models
  (:require [food_truck.db :as db]
            [clojure.java.jdbc :as sql]
            [clojure.java.io :as io])
  (:use clojure.test))

(def dbname "test.sqlite")

(defonce db-connection {:subprotocol "sqlite" :subname dbname})


(defn remove-db-file [file-name]
    (io/delete-file file-name))

(defn drop-tables! [db]
    (try
        (sql/db-do-commands db-connection
            (sql/drop-table-ddl :food_truck))
    (catch Exception e (str "caught exception: " (.getMessage e)))))

;; setup and teardown function that clears sqlite tables, creates relevant tables and 
;; then clears them up.
(defn setup [test]
    (drop-tables! db-connection)
    (db/initial-schema db-connection)
    (test)
    (drop-tables! db-connection)
    (remove-db-file dbname))


(use-fixtures :each setup)

(deftest test-create
  (testing "create a new one"
    (db/create "Marks Shack" 45 45 "Shacks" db-connection)
    (is (= (count(db/find-all db-connection)) 1))))

(deftest test-find-id
    (testing "find a specific id"
        (db/create "Marks Shack" 45 45 "Shacks" db-connection)
        (let [id ((first (db/find-all db-connection)) :id)]
            (is (= ((db/find-id id db-connection) :id) id)))))

(deftest test-find-id-nothing
    (testing "get an empty result"
        (is (= (db/find-id 43 db-connection) nil))))

(deftest test-find-all-nothing
    (testing "get an empty result"
        (is (= (db/find-all db-connection) ()))))

