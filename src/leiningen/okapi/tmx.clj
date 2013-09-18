(ns leiningen.okapi.tmx
  "tmx - extract a tmx file from input files."
  (:require [clojure.java.io :as io]
            [stencil.core :as stencil]
            [leiningen.classpath :as cp]
            [leiningen.new.templates :as tmpl]
            [leiningen.core [eval :as eval] [main :as main]]
            [clojure.string :as string]))

(defn tmx [& args]
  (println "okapi tmx...someday"))
