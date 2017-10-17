(require #?(:clj '[clojure.java.io :as io]
            :cljs '[fs])
         '[clojure.string :as str]
         #?(:cljs '[twitter-text :as twitter]))

(defn read-file
  "Return file contents as string."
  [file]
  #?(:clj  (slurp file)
     :cljs (fs/readFileSync file "utf8")))

(defn read-dir
  "Return list of filenames in a dir."
  [dir]
  #?(:clj (map #(.getName %) (file-seq (io/file dir)))
     :cljs (fs/readdirSync dir)))

(defn valid-tweet? [text]
  #?(:clj  (.isValidTweet (com.twitter.Validator.) text)
     :cljs (.isValidTweetText twitter text)))

(def tweets (into #{} (str/split (read-file "tweets.txt") #"\n%\n")))

(def quotes (->> (read-dir "../fortune-vn/data")
                 (filter #(re-matches #".*\.txt$" %))
                 (map #(read-file (str "../fortune-vn/data/" %)))
                 (mapcat #(str/split % #"\n%\n"))
                 (map str/trim)))

(print (->> quotes
            (filter valid-tweet?)
            (remove #(contains? tweets %))
            rand-nth))
