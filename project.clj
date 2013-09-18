(defproject lein-okapi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [leiningen "2.3.1"]
                 [org.clojure/tools.cli "0.2.2"]
                 [net.sf.okapi/okapi-core "0.22"]
                 [net.sf.okapi/okapi-lib "0.22"]
                 [net.sf.okapi.filters/okapi-filter-archive "0.22"]
                 [net.sf.okapi.filters/okapi-filter-doxygen "0.22"]
                 [net.sf.okapi.filters/okapi-filter-json "0.22"]
                 [net.sf.okapi.filters/okapi-filter-mosestext "0.22"]
                 [net.sf.okapi.filters/okapi-filter-rainbowkit "0.22"]
                 [net.sf.okapi.filters/okapi-filter-railsyaml "0.22"]
                 [net.sf.okapi.filters/okapi-filter-transifex "0.22"]
                 [net.sf.okapi.filters/okapi-filter-txml "0.22"]
                 [net.sf.okapi.filters/okapi-filter-wiki "0.22"]
                 [net.sf.okapi.filters/okapi-filter-xliff "0.22"]
                 [net.sf.okapi.filters/okapi-filter-xmlstream "0.22"]
                 [net.sf.okapi.lib/okapi-lib-preprocessing "0.22"]
                 [log4j "1.2.17" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [org.slf4j/slf4j-log4j12 "1.6.6"]
                 [org.clojure/tools.logging "0.2.3"]]

                 ;; [stencil "0.3.2"]
                 ;; [leinjacker "0.4.1"]
                 ;; [lein-libdir "0.1.1"]]
  :eval-in-leiningen true)
