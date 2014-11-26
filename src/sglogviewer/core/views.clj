(ns sglogviewer.core.views
  (:require
    clojure.java.io
    [clojure.pprint :refer [pprint]]
    [clj-time.core :as time]
    [clj-time.format :as time-format]
    [hiccup.core :as h]
    [hiccup.page :as p]
    [hiccup.element :as e]
    [hiccup.form :as form]
    [cemerick.friend :as friend]
    (cemerick.friend [workflows :as workflows]
                     [credentials :as creds])))

(defn main-page []
  (p/html5 [:head "SG LogViewer"]
           [:body
            [:p "SG Logviewer welcome"]]))





