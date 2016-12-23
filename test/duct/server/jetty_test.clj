(ns duct.server.jetty-test
  (:import java.net.ConnectException)
  (:require [clj-http.client :as http]
            [clojure.test :refer :all]
            [duct.server.jetty :refer :all]
            [integrant.core :as ig]))

(deftest init-and-halt-test
  (let [response {:status 200 :headers {} :body "test"}
        handler  (constantly response)
        config   {:duct.server/jetty {:port 3400, :handler handler}}]
    (testing "server starts"
      (let [system (ig/init config)]
        (try
          (let [response (http/get "http://127.0.0.1:3400/")]
            (is (= (:status response) 200))
            (is (= (:body response) "test")))
          (finally
            (ig/halt! system)))))
    (testing "server stops"
      (is (thrown? ConnectException (http/get "http://127.0.0.1:3400/"))))))

(deftest resume-and-suspend-test
  (let [response1 {:status 200 :headers {} :body "foo"}
        response2 {:status 200 :headers {} :body "bar"}
        config1   {:duct.server/jetty {:port 3400, :handler (constantly response1)}}
        config2   {:duct.server/jetty {:port 3400, :handler (constantly response2)}}]

    (testing "suspend and resume"
      (let [system1  (doto (ig/init config1) ig/suspend!)
            response (future (http/get "http://127.0.0.1:3400/"))
            system2  (ig/resume config2 system1)]
        (try
          (is (identical? (-> system1 :duct.server/jetty :handler)
                          (-> system2 :duct.server/jetty :handler)))
          (is (identical? (-> system1 :duct.server/jetty :server)
                          (-> system2 :duct.server/jetty :server)))
          (is (= (:status @response) 200))
          (is (= (:body @response) "bar"))
          (finally
            (ig/halt! system1)
            (ig/halt! system2)))))

    (testing "suspend and resume with different config"
      (let [system1  (doto (ig/init config1) ig/suspend!)
            config2' (assoc-in config2 [:duct.server/jetty :port] 3401)
            system2  (ig/resume config2' system1)]
        (try
          (is (-> system1 :duct.server/jetty :server .isStopped))
          (let [response (http/get "http://127.0.0.1:3401/")]
            (is (= (:status response) 200))
            (is (= (:body response) "bar")))
          (finally
            (ig/halt! system1)
            (ig/halt! system2)))))

    (testing "suspend and resume with extra config"
      (let [system1 (doto (ig/init {}) ig/suspend!)
            system2 (ig/resume config2 system1)]
        (try
          (let [response (http/get "http://127.0.0.1:3400/")]
            (is (= (:status response) 200))
            (is (= (:body response) "bar")))
          (finally
            (ig/halt! system2)))))))
