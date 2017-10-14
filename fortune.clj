(require '[clojure.java.io :as io]
         '[clojure.java.shell :refer [sh]]
         '[clojure.string :as str])

;; twitter
(def tweets (into #{} (str/split (slurp "tweets.txt") #"\n%\n")))

(defn twit [msg]
  (sh "bash" "-c" (str "twurl -d 'status=" msg "' /1.1/statuses/update.json")))

;; github
(def quotes (->> (io/file "../fortune-vn/data")
                 file-seq
                 (filter #(.isFile %))
                 (filter #(re-matches #".*\.txt$" (.getName %)))
                 (map slurp)
                 (mapcat #(str/split % #"\n%\n"))
                 (map str/trim)))

;; main
(let [quote (->> quotes
                 (filter #(<= (count %) 140))
                 (remove #(contains? tweets %))
                 rand-nth)]
  (println "twitting quote:\n" quote)
  (twit quote)
  (spit "tweets.txt" (str quote "\n%\n") :append true))
