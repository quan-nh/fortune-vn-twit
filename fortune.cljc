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
  #?(:clj  (.isValid (com.twitter.twittertext.TwitterTextParser/parseTweet text))
     :cljs (:valid (js->clj (.parseTweet twitter text) :keywordize-keys true))))

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
