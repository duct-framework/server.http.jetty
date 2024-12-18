(defproject org.duct-framework/server.http.jetty "0.3.0"
  :description "Integrant methods for running a Jetty web server"
  :url "https://github.com/duct-framework/server.http.jetty"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.4"]
                 [org.duct-framework/logger "0.4.0"]
                 [integrant "0.13.0"]
                 [ring/ring-jetty-adapter "1.13.0"]]
  :profiles
  {:dev {:dependencies [[clj-http "3.13.0"]]}})
