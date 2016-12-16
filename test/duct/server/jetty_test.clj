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
