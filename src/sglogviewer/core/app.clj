(ns sglogviewer.core.app
  (:require
    clojure.java.io
    clojure.string
    [clojure.pprint :refer [pprint]]
    [clj-time.core :as time]
    [clj-time.format :as time-format]))

(def log-recs (atom {}))

(def testlog1 "testdata/devMultiRack_SG_A.log")
(def testlog2 "testdata/devMultiRack_SG_B.log")

;;;;;  http://www.joda.org/joda-time/key_format.html
;(def sglog-data-formatter (f/formatter "yyyyMMdd"))
;2014-Nov-05 13:26:57.140779
(def sglog-data-formatter (time-format/formatter "yyyy-MMM-dd HH:mm:ss.SSSSSS"))

(defn parse-date [str]
  (let [d-str (subs str 0 27)]
    (time-format/parse-local sglog-data-formatter d-str)))

(defn parse-log-str [str]
  (let [d (parse-date str)]
    {:date d
     :str (subs str 28)}))

(defn add-to-log-recs [str]
  (let [r (parse-log-str str)]
    (swap! log-recs assoc (:date r) r)))

(defn read-file [fname]
  (with-open [rdr (clojure.java.io/reader fname)]
    (doall (map add-to-log-recs (line-seq rdr)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def s1 "2014-Nov-05 13:26:57.140779 [General] [TRACE] ring_db_type::send_ctrl_msg():STOP m_state: 0")

(defn test-date-parse []
  (time-format/parse-local sglog-data-formatter "2014-Nov-05 13:26:57.140779 [General] [TRACE] "))

(defn ttt []
  (read-file testlog1))