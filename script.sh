#! /usr/bin/env bash

cd ../fortune-vn
git pull

cd ../fortune-vn-twit
QUOTE="$(clj fortune.clj)"
#echo "$QUOTE"
twurl -d "status=$QUOTE" /1.1/statuses/update.json
echo "$QUOTE" >>tweets.txt
echo "%" >>tweets.txt
