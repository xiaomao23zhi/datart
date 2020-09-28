#!/bin/bash

#Use es network
docker network create esnetwork

#
docker run -d --name kibana --net esnetwork -p 5601:5601 kibana