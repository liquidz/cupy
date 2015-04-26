(ns cupy.core
  (:gen-class)
  (:require
    [clojure.tools.cli :as cli]
    [clojure.java.io   :as io]
    [cuma.core         :as cuma]
    [clj-yaml.core     :as yaml]
    [clojure.edn       :as edn]
    [clojure.data.json :as json]))

(defn- load-yaml-data
  [file]
  (-> file slurp yaml/parse-string))

(defn- load-edn-data
  [file]
  (-> file slurp edn/read-string))

(defn- load-json-data
  [file]
  (-> file slurp (json/read-str :key-fn keyword)))

(defn load-data
  [file]
  (condp #(.endsWith %2 %1) file
    ".yml"  (load-yaml-data file)
    ".edn"  (load-edn-data file)
    ".json" (load-json-data file)
    nil))

(defn render
  [template-file data-files]
  (->> data-files
       (map load-data)
       (apply merge)
       (cuma/render (slurp template-file))))

(def ^:private cli-options
  [["-h" "--help" "Show this help"]])

(defn- show-help
  [summary]
  (doseq [s ["Usage:"
             "  java -jar cupy-standalone.jar [ARGUMENTS]"
             "Arguments:"
             "  [OPTIONS] [TEMPLATE FILE] [DATA FILE1] ... [DATA FILEn]"
             "Options:"
             summary]]
    (println s)))

(defn -main
  [& args]
  (let [{:keys [options arguments summary]} (cli/parse-opts args cli-options)
        [template-file & data-files] arguments]
    (cond
      (:help options) (show-help summary)
      :else
      (println (render template-file data-files)))))
