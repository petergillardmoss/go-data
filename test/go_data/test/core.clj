(ns go-data.test.core
  (:use [go-data.core] [feedparser-clj.core])
  (:use [clojure.test]))

(def test-feed {:entries [{:uri "https://go01.thoughtworks.com/go/pipelines/pipeline-one/93/test/1"}
                          {:uri "https://go01.thoughtworks.com/go/pipelines/pipeline-two/13/build/4"}]})

(deftest transforms-a-simple-feed
  (is (= [{:pipeline "pipeline-one" :pipeline-counter 93 :stage "test" :stage-counter 1}
          {:pipeline "pipeline-two" :pipeline-counter 13 :stage "build" :stage-counter 4}]
         (transform test-feed))))
