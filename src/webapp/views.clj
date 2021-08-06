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
   [:a {:href "/add-location"} "Add a Location"]
   " | "
   [:a {:href "/all-locations"} "View all Locations"]
   " |"
   [:a {:href "/add-paste"} "Add a paste"]
   " | "
   [:a {:href "/all-pastes"} "View all pastes"]
   " ]"])

(defn home-page
  []
  (page/html5
    (gen-page-head "Home")
    header-links
    [:h1 "Home"]
    [:p "This web app stores and displays 2D (x,y) locations."]))

(defn add-location-page
  []
  (page/html5
    (gen-page-head "Add a Location")
    header-links
    [:h1 "Add a Location"]
    [:form {:action "/add-location" :method "POST"}
     (util/anti-forgery-field) ; prevents cross-site scripting attacks
     [:p "x value: " [:input {:type "text" :name "x"}]]
     [:p "y value: " [:input {:type "text" :name "y"}]]
     [:p [:input {:type "submit" :value "submit location"}]]]))

(defn add-location-results-page
  [{:keys [x y]}]
  (let [id (db/add-location-to-db x y)]
    (page/html5
      (gen-page-head "Added a Location")
      header-links
      [:h1 "Added a Location"]
      [:p "Added [" x ", " y "] (id: " id ") to the db. "
       [:a {:href (str "/location/" id)} "See for yourself"]
       "."])))



(defn location-page
  [loc-id]
  (let [{x :x y :y} (db/get-xy loc-id)]
    (page/html5
      (gen-page-head (str "location " loc-id))
      header-links
      [:h1 "A given location"]
      [:p "id: " loc-id]
      [:p "x: " x]
      [:p "y: " y])))

(defn all-locations-page
  []
  (let [all-locs (db/get-all-locations)]
    (page/html5
      (gen-page-head "All locations in the database")
      header-links
      [:h1 "All locations"]
      [:table
       [:tr [:th "id"] [:th "x"] [:th "y"]]
       (for [loc all-locs]
         [:tr [:td (:id loc)] [:td (:x loc)] [:td (:y loc)]])])))



(defn add-paste-page
  []
  (page/html5
    (gen-page-head "Add a Paste")
    header-links
    [:h1 "Add a Paste!"]
    [:form {:action "/add-paste" :method "POST"}
     (util/anti-forgery-field) ; prevents cross-site scripting attacks
     [:p "content: " [:input {:type "text" :name "content"}]]
     [:p [:input {:type "submit" :value "submit paste"}]]]))


(defn add-paste-results-page
  [{:keys [content]}]
  (let [id (db/add-paste-to-db content)]
    (page/html5
      (gen-page-head "Pasted!")
      header-links
      [:h1 "Pasted!"]
      [:p "Added (id: " id ") to the db. "
       [:a {:href (str "/paste/" id)} "More"]
       "."])))

(defn paste-page
  [paste-id]
  (let [{content :content} (db/get-paste paste-id)]
    (page/html5
      (gen-page-head (str "paste " paste-id))
      header-links
      [:h1 "id: " paste-id]
      [:p content])))

(defn trunc
  [s n]
  (if (> (count s) n)
    (str (apply str (take n s)) "...")
    s))

(defn all-pastes-page
  []
  (let [all-pastes (db/get-all-pastes)]
    (page/html5
      (gen-page-head "All pastes in the database")
      header-links
      [:h1 "All pastes"]
      [:table
       [:tr [:th "id"] [:th "content"]]
       (for [paste all-pastes]
         [:tr [:td (:id paste)] [:td (trunc (get paste :content) 34)]])])))