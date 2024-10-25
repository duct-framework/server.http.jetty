(defproject duct/server.http.jetty "0.2.2"
  :description "Integrant methods for running a Jetty web server"
  :url "https://github.com/duct-framework/server.http.jetty"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.4"]
                 [duct/core "0.8.1"]
                 [duct/logger "0.3.0"]
                 [integrant "0.13.0"]
                 [ring/ring-jetty-adapter "1.13.0"]]
  :profiles
  {:dev {:dependencies [[clj-http "3.12.1"]]}})
