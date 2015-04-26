(ns cupy.core-test
  (:require
    [clojure.string :as str]
    [clojure.test   :refer :all]
    [cupy.core      :refer :all]))

(defn- test-load-data
  [file]
  (load-data (str "test/files/load_data/" file)))

(defn- test-render
  [tmpl data]
  (let [test-dir "test/files/render/"]
    (render (str test-dir tmpl)
            (map #(str test-dir %) data))))

(deftest load-data-test
  (let [expected {:hello "world" :foo "bar"}]
    (are [file] (= expected (test-load-data file))
      "test.yml"
      "test.json"
      "test.edn"))
  (is (nil? (load-data "foo"))))

(deftest render-test
  (are [x y] (= x (str/trim (apply test-render y)))
    "hello YAML world" ["test.tmpl" ["data.yml"]]
    "hello EDN world"  ["test.tmpl" ["data.edn"]]
    "hello EDN world"  ["test.tmpl" ["data.yml" "data.edn"]]
    "hello YAML world" ["test.tmpl" ["data.edn" "data.yml"]]))
