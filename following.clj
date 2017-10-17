(require '[clojure.java.shell :refer [sh]]
         '[cheshire.core :as json])

(defn following [user-id]
  (sh "bash" "-c" (str "twurl -d 'user_id=" user-id "&follow=true' /1.1/friendships/create.json")))

(def followers (-> (:out (sh "twurl" "/1.1/followers/ids.json"))
                   (json/parse-string true)
                   :ids))

(doall (map following followers))
(shutdown-agents)
