(ns go-data.core
  (:use feedparser-clj.core
        [clojure.string :only (split)])
  (:require [clj-http.client :as client]))


(defn get-feed [url credentials]
  (parse-feed ((client/get url {:basic-auth credentials :as :stream}) :body)))

(defn transform [feed]
  (let [fix (fn [h] (assoc h
                      :pipeline-counter (read-string (h :pipeline-counter))
                      :stage-counter (read-string (h :stage-counter))))]
    (into []
          (map #(fix(zipmap
                     [:pipeline :pipeline-counter :stage :stage-counter]
                     (split (last (split (% :uri) #"pipelines/")) #"/")))
               (feed :entries)))))

(defn go-feed
  [pipeline server username password]
  (transform (get-feed (str "https://" server "/go/api/pipelines/" pipeline "/stages.xml") [username password])))
