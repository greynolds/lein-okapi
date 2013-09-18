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


;; private void loadFromPluginsAndUpdate ()
;; {
;;  // Discover and add plug-ins
;;  PluginsManager mgt = new PluginsManager();
;;  mgt.discover(new File(getAppRootDirectory()+File.separator+"dropins"), true);
;;  fcMapper.addFromPlugins(mgt);
;;  }

(defn setup-plugins
  [config-mgr]
  (let [plugins-mgr (PluginsManager.)]
;        f ... dropins?]
    (do
      (.discover plugins-mgr  (io/file "./dropins") true)
      (.addFromPlugins config-mgr plugins-mgr)
      )))

  ;; private void initialize ()
  ;; {
  ;;  // Create the mapper and load it with all parameters editor info
  ;;  fcMapper = new FilterConfigurationMapper();
  ;;  DefaultFilters.setMappings(fcMapper, false, false);
  ;;  // Instead create a map with extensions -> filter
  ;;  extensionsMap = new Hashtable<String, String>();
  ;;  extensionsMap.put(".docx", "okf_openxml");
  ;;  extensionsMap.put(".pptx", "okf_openxml");
  ;;  extensionsMap.put(".xlsx", "okf_openxml");
  ;;  ...
  ;; if ( specifiedConfigIdPath != null )
  ;;   {
  ;;    fcMapper.setCustomConfigurationsDirectory(specifiedConfigIdPath);
  ;;    }
  ;;   loadFromPluginsAndUpdate();
  ;;  }
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


;; private void showAllConfigurations ()
;; {
;;  initialize();
;;  // Add the custom configurations
;;  fcMapper.updateCustomConfigurations();

;;  LOGGER.info("List of all filter configurations available:");
;;  Iterator<FilterConfiguration> iter = fcMapper.getAllConfigurations();
;;  FilterConfiguration config;
;;  while ( iter.hasNext() ) {
;;                            config = iter.next();
;;                            LOGGER.info(" - {} = {}", config.configId, config.description);
;;                            }
;;  }
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


