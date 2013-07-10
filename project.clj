(defproject go-data "1.0.0-SNAPSHOT"
  :description "Mine Go for data"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.cli "0.2.2"]]
  :main go-data.bin
  :aot [go-data.bin])