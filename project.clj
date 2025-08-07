(defproject org.duct-framework/server.http.jetty "0.3.1"
  :description "Integrant methods for running a Jetty web server"
  :url "https://github.com/duct-framework/server.http.jetty"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.12.1"]
                 [org.duct-framework/logger "0.4.0"]
                 [integrant "0.13.1"]
                 [ring/ring-jetty-adapter "1.14.2"]]
  :profiles
  {:dev {:dependencies [[clj-http "3.13.1"]]}})
