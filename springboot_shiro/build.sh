#!/usr/bin/env bash
app=songshushenghuo
host=115.236.54.154

git add *
git reset --hard
git pull

mvn clean package
scp -r ./target/*.jar test@$host:/data/service/$app/api/

ssh test@$host "cd /data/service/$app/api; sh restart.sh;"