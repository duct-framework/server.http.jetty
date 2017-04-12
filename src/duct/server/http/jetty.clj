(ns duct.server.http.jetty
  (:import org.eclipse.jetty.server.Server)
  (:require [duct.logger :refer [log]]
            [integrant.core :as ig]
            [ring.adapter.jetty :as jetty]))

(derive :duct.server.http/jetty :duct.server/http)

(defmethod ig/init-key :duct.server.http/jetty [_ {:keys [logger] :as opts}]
  (let [handler (atom (delay (:handler opts)))
        logger  (atom logger)
        options (-> opts
                    (dissoc :handler :logger)
                    (assoc :join? false))]
    (log @logger :info ::starting-server (select-keys opts [:port]))
    {:handler handler
     :logger  logger
     :server  (jetty/run-jetty (fn [req] (@@handler req)) options)}))

(defmethod ig/halt-key! :duct.server.http/jetty [_ {:keys [server logger]}]
  (log @logger :info ::stopping-server)
  (.stop ^Server server))

(defmethod ig/suspend-key! :duct.server.http/jetty [_ {:keys [handler]}]
  (reset! handler (promise)))

(defmethod ig/resume-key :duct.server.http/jetty [key opts old-opts old-impl]
  (if (= (dissoc opts :handler :logger) (dissoc old-opts :handler :logger))
    (do (deliver @(:handler old-impl) (:handler opts))
        (reset! (:logger old-impl) (:logger opts))
        old-impl)
    (do (ig/halt-key! key old-impl)
        (ig/init-key key opts))))
