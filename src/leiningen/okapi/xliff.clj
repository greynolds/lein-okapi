(ns leiningen.okapi.xliff
  "xliff - extract xliff files from input files."
  (:require [clojure.java.io :as io]
            [stencil.core :as stencil]
            [leiningen.classpath :as cp]
            [leiningen.new.templates :as tmpl]
            [leiningen.core [eval :as eval] [main :as main]]
            [clojure.string :as string]))

(defn xliff [& args]
  (println "okapi xliff...someday"))
