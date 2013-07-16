(ns go-data.test.core
  (:use [go-data.core] [feedparser-clj.core])
  (:use [clojure.test]))

(def test-feed {:entries [{:uri "https://go01.thoughtworks.com/go/pipelines/pipeline-one/93/test/1"}
                          {:uri "https://go01.thoughtworks.com/go/pipelines/pipeline-two/13/build/4"}]})

(deftest transforms-a-simple-feed
  (is (= [{:pipeline "pipeline-one" :pipeline-counter 93 :stage "test" :stage-counter 1}
          {:pipeline "pipeline-two" :pipeline-counter 13 :stage "build" :stage-counter 4}]
         (transform test-feed))))

(deftest groups-a-feed-by-pipeline
  (let [transformed-feed [{:pipeline-counter 1 :stage "test"}
                          {:pipeline-counter 1 :stage "build"}
                          {:pipeline-counter 2 :stage "integration"}
                          {:pipeline-counter 2 :stage "build"}]]
    (is (= {1 [{:pipeline-counter 1 :stage "test"}
            {:pipeline-counter 1 :stage "build"}]
            2 [{:pipeline-counter 2 :stage "integration"}
               {:pipeline-counter 2 :stage "build"}]}
           (group-feed transformed-feed)))))


(deftest returns-url-for-valuestream
  (is (= "https://myserver/go/pipelines/value_stream_map/mypipeline/14.json"
      (valuestream-url "myserver" {:pipeline-counter 14,
                                   :pipeline "mypipeline"}))))