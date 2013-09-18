(ns leiningen.okapi.configs
  "configs - show all filter available configurations"
  (:import [net.sf.okapi.common.filters
            DefaultFilters FilterConfiguration FilterConfigurationMapper]
           [net.sf.okapi.common.plugins PluginsManager])
  (:require [clojure.java.io :as io]
            [stencil.core :as stencil]
            [leiningen.classpath :as cp]
            [leiningen.new.templates :as tmpl]
            [leiningen.core [eval :as eval] [main :as main]]
            [clojure.string :as string]
            [clojure.tools.logging :as log :only [debug info]]))

(defn configs [& args]
  (let [config-mgr (FilterConfigurationMapper.)
        plugins-mgr (PluginsManager.)]
    (do
      (DefaultFilters/setMappings config-mgr false true)
      (.discover plugins-mgr  (io/file "./dropins") true)
      (.addFromPlugins config-mgr plugins-mgr)
      (.updateCustomConfigurations config-mgr)
      (println "Listing available filter configurations")
      (let [configs (.getAllConfigurations config-mgr)]
        (doseq [config (sort-by #(.name %) (iterator-seq configs))]
          (println (format "%s: %s" ;; = %s"
                           (.name config)
                           (.configId config)
                           ;;(.description config)
                           )))))
  ))


