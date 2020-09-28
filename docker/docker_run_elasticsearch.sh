#!/bin/bash

#Create network
docker network create esnetwork

docker run -d --name elasticsearch --net esnetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch