(defproject duct/server.http.jetty "0.1.2"
  :description "Integrant methods for running a Jetty web server"
  :url "https://github.com/duct-framework/server.http.jetty"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [duct/logger "0.1.1"]
                 [integrant "0.3.3"]
                 [ring/ring-jetty-adapter "1.6.0"]]
  :profiles
  {:dev {:dependencies [[clj-http "2.1.0"]]}})
