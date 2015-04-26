(defproject cupy "0.1.0"
  :description "A tool to generate text file from template."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [circleci/clj-yaml "0.5.3"]
                 [cuma "0.1.1"]]
  :main cupy.core
  :aot [cupy.core]
  :uberjar-name "cupy-standalone.jar")
