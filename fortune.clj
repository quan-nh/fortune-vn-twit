(require '[clojure.java.io :as io]
         '[clojure.string :as str])

(def tweets (into #{} (str/split (slurp "tweets.txt") #"\n%\n")))

(def quotes (->> (file-seq (io/file "../fortune-vn/data"))
                 (filter #(re-matches #".*\.txt$" (.getName %)))
                 (map slurp)
                 (mapcat #(str/split % #"\n%\n"))
                 (map str/trim)))

(print (->> quotes
            (filter #(.isValidTweet (com.twitter.Validator.) %))
            (remove #(contains? tweets %))
            rand-nth))
