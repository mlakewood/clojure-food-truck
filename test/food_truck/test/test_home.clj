(ns food_truck.test.test_home
    (:use clojure.test
          ring.mock.request
          food_truck.routes.home))

(deftest test-home
    (testing "get-coords"
        (let [params "45,34"]
            (is (= (get-coords params) {:latitude 45 :longitude 34}))
            ))
    (testing "get-coords empty"
        (let [params nil]
              (is (= (get-coords params) nil))
            )))
    

(deftest test-get-db-coords
    (testing "test the get-db-cords"
        (let [data {:fooditems "shack", :lng 56.0, :lat 45.0, :name "ssssmarkss sshacksss", :id 128}]
            (is (= (get-db-cords data) {:latitude 45.0 :longitude 56.0})))))