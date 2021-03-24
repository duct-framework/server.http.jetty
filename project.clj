(defproject duct/server.http.jetty "0.2.1"
  :description "Integrant methods for running a Jetty web server"
  :url "https://github.com/duct-framework/server.http.jetty"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [duct/core "0.8.0"]
                 [duct/logger "0.3.0"]
                 [integrant "0.8.0"]
                 [ring/ring-jetty-adapter "1.9.2"]]
  :profiles
  {:dev {:dependencies [[clj-http "3.12.1"]]}})
