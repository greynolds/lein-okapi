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


(defn setup-plugins
  [config-mgr]
  (let [plugins-mgr (PluginsManager.)]
;        f ... dropins?]
    (do
      (.discover plugins-mgr  (io/file "./dropins") true)
      (.addFromPlugins config-mgr plugins-mgr)
      )))

(defn setup
  [config-mgr]
  (let [filter-map {  ;; what's the purpose of this?
                    ".docx" "okf_openxml"
                    ".pptx" "okf_openxml"
                    ".xlsx" "okf_openxml"
                    ;; ...
                    }]
    (do
      (DefaultFilters/setMappings config-mgr false true)
      (setup-plugins config-mgr)))
  )

(defn configs [& args]
  (let [config-mgr (FilterConfigurationMapper.)]
    (setup config-mgr)
    (.updateCustomConfigurations config-mgr)
    (println "Listing available filter configurations")
    (let [configs (.getAllConfigurations config-mgr)]
      (do ;; (println "configs:" (type configs))
      (doseq [config (sort-by #(.name %) (iterator-seq configs))]
             (println (format "%s: %s" ;; = %s"
                              (.name config)
                              (.configId config)
                              ;;(.description config)
                              )))))
    ))


