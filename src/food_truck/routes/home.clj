(ns food_truck.routes.home
  (:require [compojure.core :refer :all]
            [ring.util.response :as response]
            [food_truck.views.layout :as layout]
            [food_truck.db :as db]
            [food_truck.routes.maths :as maths]))

(defn home [] 
  (layout/common [:h1 "Hello World!"]))

; (defn number [data]
;     (if ))

(defn get-coords [coords]
    (if coords
        (zipmap [:latitude :longitude ] 
                (map read-string (clojure.string/split coords #",")))
    nil))

(defn get-db-cords [truck]
    (let [long (truck :lng) lat (truck :lat)]
        {:longitude long :latitude lat}))

(defn filter-trucks [truck location distance]
    (if (< (maths/calculate-distance (get-db-cords truck) location) distance) 
        truck))

(defn trucks-list [params]
    (let [current-location (get-coords (:current_location params "0,0"))
          distance (Float. (:distance params "0"))
          trucks (db/find-all db/db-connection)
          body  (filter identity (map #(filter-trucks % current-location distance) trucks))]
    (if (and (= distance 0.0) (= current-location {:longitude 0, :latitude 0}))
        {:body trucks}
        {:body body})))

(defn truck-instance [id]
    (let [body (db/find-id id db/db-connection)]
        {:body body}))

(defroutes home-routes
  (GET "/" [] (response/file-response "index.html" {:root "public"}))
  (GET "/food_trucks" {params :params} [] (trucks-list params))
  (GET "/food_trucks/:id" [id] (truck-instance id)))
