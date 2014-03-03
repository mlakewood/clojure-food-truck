(ns food_truck.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.json :as middleware]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [com.duelinmarkers.ring-request-logging :refer [wrap-request-logging]]
            [food_truck.routes.home :refer [home-routes]]))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes app-routes)
      (handler/api)
      (wrap-request-logging)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))