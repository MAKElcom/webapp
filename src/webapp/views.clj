(ns webapp.views
  (:require [webapp.db :as db]
            [clojure.string :as str]
            [hiccup.page :as page]
            [ring.util.anti-forgery :as util]))

(defn gen-page-head
  [title]

  [:head
   [:title (str "Locations: " title)]
   (page/include-css "/css/styles.css")])

(def header-links
  [:div#header-links
   "[ "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/add-locations"} "Add a Location"]
   " | "
   [:a {:href "/all-locations"} "View all Locations"]
   " ]"])

(defn home-page
  []
  (page/html5
    (gen-page-head "Home")
    header-links
    [:h1 "Home"]
    [:p "This web app stores and displays 2D (x,y) locations."]))
