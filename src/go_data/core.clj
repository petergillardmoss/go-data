(ns go-data.core
  (:use feedparser-clj.core
        [clojure.string :only (split)])
  (:require [clj-http.client :as client]))


(defn get-feed [url credentials]
  (parse-feed ((client/get url {:basic-auth credentials :as :stream}) :body)))

(defn interesting-url-parts [url] 
    ;; little awkward - look up url templating libraries for clojure 
    (let [[_ right-part] (re-find #"pipeline/(.*)" url)] 
      (clojure.string/split right-part #"/")))

(defn url-to-feed-data [url]
  (->
   (zipmap [:pipeline :pipeline-counter :stage :stage-counter] (interesting-url-parts url))
   (update-in [:pipeline-counter] #(Integer/parseInt %))
   (update-in [:stage-counter] #(Integer/parseInt %))))

(defn transform [feed]
    (map url-to-feed-data (map :uri (feed :entries))))

(defn go-feed
  [pipeline server username password]
  (transform (get-feed (str "https://" server "/go/api/pipelines/" pipeline "/stages.xml") [username password])))
