(ns food_truck.test.test_handler
  (:use clojure.test
        ring.mock.request
        food_truck.handler))

; (deftest test-app
;  (testing "main route"
;    (let [response (app (request :get "/"))]
;      (is (= (:status response) 200))
;      (is (= (:body response) "Hello World"))))

;  (testing "not-found route"
;    (let [response (app (request :get "/invalid"))]
;      (is (= (:status response) 404)))))
