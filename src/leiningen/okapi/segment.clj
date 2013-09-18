(ns leiningen.okapi.segment
  "segment - apply SRX segmentation rules to input files."
  (:require [clojure.java.io :as io]
            [stencil.core :as stencil]
            [leiningen.classpath :as cp]
            [leiningen.new.templates :as tmpl]
            [leiningen.core [eval :as eval] [main :as main]]
            [clojure.string :as string]))

(defn segment [& args]
  (println "okapi segment...someday"))
