(ns go-data.bin
  (:require go-data.core)
  (:use go-data.core
        [clojure.tools.cli :only [cli]])
  (:gen-class))

(defn args->go-args [args]
  (cli args
       ["-s" "--server"]
       ["-u" "--username"]
       ["-p" "--password"]))

(defn go-args->go-feed-args [go-args]
  (let [options (go-args 0)
        pipeline-name (first (go-args 1))]
    (flatten [pipeline-name (map options [:server :username :password])])))

(defn -main [& args]
  (println (apply go-feed (go-args->go-feed-args (args->go-args args)))))