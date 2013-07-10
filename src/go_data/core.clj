(ns go-data.core)

(defn go-feed
  [pipeline server username password]
  {:pipeline pipeline :server server :username username :password password})