(defproject duct/jetty-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [integrant "0.1.5"]
                 [ring/ring-jetty-adapter "1.5.0"]]
  :profiles
  {:dev {:dependencies [[clj-http "2.1.0"]]}})
