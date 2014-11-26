(ns sglogviewer.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [sglogviewer.core.views :as views]))

(defroutes app-routes
  (GET "/" [] (views/main-page))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

