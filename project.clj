(defproject clojam "0.1.0-SNAPSHOT"
  :description "A Clojure library for Google Code Jam."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :profiles {
    :dev {
      :dependencies [[midje "1.5.0"]]
      :plugins [[lein-midje "3.0.0"]]}})
