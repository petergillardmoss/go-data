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
  [(first (go-args 1)) ((go-args 0) :server) ((go-args 0) :username) ((go-args 0) :password)])

(defn -main [& args]
  (println (apply go-feed (go-args->go-feed-args (args->go-args args)))))