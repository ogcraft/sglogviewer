(defproject sglogviewer "0.1.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.2.0"]
                 [ring/ring-defaults "0.1.2"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [clj-time "0.8.0"]
                 [hiccup "1.0.5"]
                 [com.cemerick/friend "0.2.1"]
                 [clj-http "1.0.1"]
                 [org.apache.httpcomponents/httpclient "4.3.5"]]

  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler sglogviewer.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
