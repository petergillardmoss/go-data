(ns go-data.test.core
  (:use [go-data.core] [feedparser-clj.core])
  (:use [clojure.test]))

(def test-feed {:entries [{:uri "http://id1"} {:uri "http://id2"}]})

(deftest transforms-a-simple-feed
  (is (= [{:id "http://id1"}, {:id "http://id2"}] (transform test-feed))))
