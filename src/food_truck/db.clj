(ns food_truck.db
  (:require [clojure.java.jdbc :as sql]))

(def dbname "./food_trucks.sqlite")

(defonce db-connection {:subprotocol "sqlite" :subname dbname})

(defn find-all [db]
    (sql/query db
        ["SELECT * FROM food_truck ORDER BY id DESC"]))

(defn find-id [id db]
    (first (sql/query db
        ["SELECT * from food_truck WHERE id=? ORDER by id DESC" id])))

(defn create [name lat lng fooditems db]
    (sql/insert! db
        :food_truck {:name name
                     :lat lat
                     :lng lng
                     :fooditems fooditems}))

(defn initial-schema [db]
    (sql/db-do-commands db
        (sql/create-table-ddl  :food_truck
                                [:id :integer "PRIMARY KEY"]
                                [:name :varchar "NOT NULL"]
                                [:lat :float "NOT NULL"]
                                [:lng :float]
                                [:fooditems :varchar])))
