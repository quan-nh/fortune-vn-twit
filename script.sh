#! /usr/bin/env bash

cd ../fortune-vn
git pull

cd ../fortune-vn-twit
QUOTE="$(lumo fortune.cljc)"
echo "$QUOTE"
TW_ID=$(twurl -d "status=$QUOTE" /1.1/statuses/update.json | jq '.id_str' | bc)
echo $TW_ID
twurl -d "id=$TW_ID" /1.1/favorites/create.json
echo "$QUOTE" >>tweets.txt
echo "%" >>tweets.txt
