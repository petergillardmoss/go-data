(ns go-data.core
  (:use feedparser-clj.core)
  (:require [clj-http.client :as client]))


(defn get-feed [url credentials]
  (parse-feed ((client/get url {:basic-auth credentials :as :stream}) :body)))

(defn transform [feed]
  (into [] (map #(hash-map :id (% :uri))
       (feed :entries))))

(defn go-feed
  [pipeline server username password]
  (transform (get-feed (str "https://" server "/go/api/pipelines/" pipeline "/stages.xml") [username password])))
