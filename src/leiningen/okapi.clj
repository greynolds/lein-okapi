(ns leiningen.okapi
  "okapi - a leiningen plugin for the Okapi Translation and Localization framework."
  (:import java.io.File
           ;; com.google.appengine.tools.admin.AppCfg
           ;; [org.apache.commons.exec CommandLine DefaultExecutor]
           )
  (:require [leiningen.okapi.xliff :as xliff]
            [leiningen.okapi.configs :as configs]
            [leiningen.okapi.segment :as segment]
            [leiningen.okapi.tmx :as tmx]
            [clojure.tools.cli :as cli]
            [clojure.tools.logging :as log :only [debug info]]))

(try ; Leiningen 2.0 compatibility
  (use '[leiningen.core :only [abort]])
  (catch Exception _
    (use '[leiningen.core.main :only [abort]])))

(def ^{:dynamic true} *app* nil) ;; what for?

;; cribbed from heroku plugin:

(def ^{:private true} cli-options
  ["-a" "--app" "App to use if not in project dir."])

(defn- task-not-found [& _]
  (abort "That's not a task. Use \"lein okapi help\" to list all subtasks."))

;; like leiningen.core, but with special subtask handling for colon-grouping
(defn- resolve-task
  ([task not-found]
     (let [task-ns (symbol (str "leiningen.okapi." (first (.split task ":"))))
           task (symbol task)]
       (try
         (when-not (find-ns task-ns)
           (require task-ns))
         (or (ns-resolve task-ns task)
             not-found)
         (catch java.io.FileNotFoundException e
           not-found))))
  ([task] (resolve-task task #'task-not-found)))

(defn #^{:subtasks [#'xliff/xliff
                    #'configs/configs
                    #'segment/segment
                    #'tmx/tmx]}
  okapi
  "run okapi tasks"
  [project cmd & args]
  (let [subtask (resolve-task (or cmd "help"))
        command cmd]
    (try
      (binding [*app* nil] ;;(:app opts)]
        (apply subtask project args))
      (catch Exception e
        (abort (or (.getMessage e) (pr e)))))))

