# fortune-vn-twit
Twitter Bot for https://github.com/icy/fortune-vn

## CLI tools
- Run on JVM w/ [Clojure scripts](https://clojure.org/guides/deps_and_cli): `clj fortune.cljc`
- Run on Node.js w/ [Lumo](https://github.com/anmonteiro/lumo): `lumo fortune.cljc`
- [Twurl](https://github.com/twitter/twurl)
- [jq](https://stedolan.github.io/jq/)

## Deps
- [twitter-text](https://github.com/twitter/twitter-text/)

** notes: Twitter API return large integers IDs, neither [jq](https://github.com/stedolan/jq/issues/369) nor [js](https://developer.twitter.com/en/docs/basics/twitter-ids) could handle it (too bad 🙈). Clj to the Rescue!
