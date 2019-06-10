(defproject duct/server.http.jetty "0.2.0"
  :description "Integrant methods for running a Jetty web server"
  :url "https://github.com/duct-framework/server.http.jetty"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [duct/core "0.7.0"]
                 [duct/logger "0.1.1"]
                 [integrant "0.7.0"]
                 [ring/ring-jetty-adapter "1.7.1"]]
  :profiles
  {:dev {:dependencies [[clj-http "3.10.0"]]}})
