(ns duct.server.jetty
  (:import org.eclipse.jetty.server.Server)
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as jetty]))

(defmethod ig/init-key :duct.server/jetty [_ opts]
  (let [handler (atom (delay (:handler opts)))
        options (-> opts (dissoc :handler) (assoc :join? false))]
    {:handler handler
     :server  (jetty/run-jetty (fn [req] (@@handler req)) options)}))

(defmethod ig/halt-key! :duct.server/jetty [_ {:keys [server]}]
  (.stop ^Server server))

(defmethod ig/suspend-key! :duct.server/jetty [_ {:keys [handler]}]
  (reset! handler (promise)))

(defmethod ig/resume-key :duct.server/jetty [key opts old-opts old-impl]
  (if (= (dissoc opts :handler) (dissoc old-opts :handler))
    (do (deliver @(:handler old-impl) (:handler opts))
        old-impl)
    (do (ig/halt-key! key old-impl)
        (ig/init-key key opts))))
