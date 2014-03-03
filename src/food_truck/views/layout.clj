(ns food_truck.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to food_truck"]
     (include-css "/css/screen.css")]
    [:body body]))
