(ns food_truck.test.test_maths
    (:use clojure.test)
    (:require [food_truck.routes.maths :as maths]))



(deftest test-validate
    (testing "validate-coords"
        (let [valid-coords {:latitude 45 :longitude 95}]
            (is (= (maths/validate-coords valid-coords) valid-coords)))))
    (testing "validate-coords invalid lat"
        (let [valid-coords {:latitude 95 :longitude 95}]
            (is (= (maths/validate-coords valid-coords) false))))
    (testing "validate-coords invalid long"
        (let [valid-coords {:latitude 45 :longitude 250}]
            (is (= (maths/validate-coords valid-coords) false))))


(deftest test-haversine
    (testing "correct calculations"
        (let [point1 {:latitude 45 :longitude 45}
              point2 {:latitude 45 :longitude 45}]
              (is (= (maths/haversine point1 point2) 0.0))))

    (testing "correct calculations"
        (let [point1 {:latitude 36.12 :longitude -86.67}
              point2 {:latitude 33.94 :longitude -118.40}]
              (is (= (maths/calculate-distance point1 point2) 1795)))))

