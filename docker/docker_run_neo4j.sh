#!/bin/sh

docker run -d --name neo4j -p 7474:7474 -p 7687:7687 --env=NEO4J_AUTH=none -v C:\Users\cmcc\Documents\neo4j:/var/lib/neo4j/import neo4j