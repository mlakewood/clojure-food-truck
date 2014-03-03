(ns food_truck.routes.maths)

(defn validate-coords [{long :longitude lat :latitude}]
    (let [max_lat 90
          min_lat -90
          max_long 180
          min_long -180]
        (if (and (<= lat max_lat) (>= lat min_lat))
            (if (and (<= long max_long) (>= long min_long))
              {:longitude long :latitude lat}
              false)
        false)))


(defn haversine [{long1 :longitude lat1 :latitude} {long2 :longitude lat2 :latitude}]
    (let [R 3961 ; miles
          deg-lat (Math/toRadians (- lat2 lat1))
          deg-long (Math/toRadians (- long2 long1))
          lat1 (Math/toRadians lat1)
          lat2 (Math/toRadians lat2)
          a (+ (* (Math/sin (/ deg-lat 2)) (Math/sin (/ deg-lat 2))) (* (Math/sin (/ deg-long 2))  (Math/sin (/ deg-long 2))  (Math/cos lat1) (Math/cos lat2)))] 
    (* R 2 (Math/asin (Math/sqrt a)))))

(defn calculate-distance [point1 point2]
    (let [[point1 point2] (map validate-coords [point1 point2])]
        (if (and (not (= point1 false))
                 (not (= point2 false)))
            (Math/round (haversine point1 point2))
            false)))