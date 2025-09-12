(defproject org.duct-framework/server.http.jetty "0.3.1"
  :description "Integrant methods for running a Jetty web server"
  :url "https://github.com/duct-framework/server.http.jetty"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.12.2"]
                 [org.duct-framework/logger "0.4.0"]
                 [integrant "1.0.0"]
                 [ring/ring-jetty-adapter "1.15.0"]]
  :profiles
  {:dev {:dependencies [[clj-http "3.13.1"]]}})
