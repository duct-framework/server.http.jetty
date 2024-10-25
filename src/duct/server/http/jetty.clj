(ns duct.server.http.jetty
  (:import org.eclipse.jetty.server.Server)
  (:require [duct.logger :as log]
            [integrant.core :as ig]
            [ring.adapter.jetty :as jetty]))

(defmethod ig/init-key :duct.server.http/jetty
  [_ {:keys [logger async?] :as opts}]
  (let [handler (atom (delay (:handler opts)))
        logger  (atom logger)
        options (-> opts
                    (dissoc :handler :logger)
                    (assoc :join? false))]
    (log/report @logger ::starting-server (select-keys opts [:port]))
    {:handler handler
     :logger  logger
     :server  (jetty/run-jetty
               (if async?
                 (fn [req resp raise] (@@handler req resp raise))
                 (fn [req] (@@handler req)))
               options)}))

(defmethod ig/halt-key! :duct.server.http/jetty [_ {:keys [server logger]}]
  (log/report @logger ::stopping-server)
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
