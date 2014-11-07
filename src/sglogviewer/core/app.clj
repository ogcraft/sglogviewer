(ns sglogviewer.core.app
  (:require [compojure.core :refer :all]
            ))

(def log-recs (atom {}))

(def testlog1 "testdata/devMultiRack_SG_A.log")
(def testlog2 "testdata/devMultiRack_SG_B.log")


(defn add-to-log-recs [str]
  (prn ">>>>" str))

(defn read-file [fname]
  (with-open [rdr (clojure.java.io/reader fname)]
    (doall (map add-to-log-recs (line-seq rdr)))))

(defn ttt []
  (read-file testlog1))