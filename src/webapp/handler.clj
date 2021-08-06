(ns webapp.handler
  (:require [webapp.views :as views] ; add this require
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes ; replace the generated app-routes with this
           (GET "/"
                []
             (views/home-page))
           (GET "/add-location"
                []
             (views/add-location-page))
           (POST "/add-location"
                 {params :params}
             (views/add-location-results-page params))
           (GET "/location/:loc-id"
                [loc-id]
             (views/location-page loc-id))
           (GET "/add-paste"
                []
             (views/add-paste-page))
           (POST "/add-paste"
                 {params :params}
             (views/add-paste-results-page params))
           (GET "/paste/:paste-id"
                [paste-id]
             (views/paste-page paste-id))
           (GET "/all-pastes"
                []
             (views/all-pastes-page))
           (GET "/all-locations"
                []
             (views/all-locations-page))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))