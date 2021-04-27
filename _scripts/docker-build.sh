#!/bin/bash
echo "Building docker images . . ."
cd $PWD
cd ../
echo $PWD

# remove containers and images
docker container rm -f $(docker ps -aq --filter="name=omurka/microservice-world-*")
docker rmi -f $(docker images --format '{{.Repository}}:{{.Tag}}' | grep 'omurka/microservice-world-')

# start building images
echo '*********************************************************************'
echo 'building api-gateway image..'
docker build -t omurka/microservice-world-api-gateway --no-cache ./api-gateway

echo '*********************************************************************'
echo 'building config-server image..'
docker build -t omurka/microservice-world-config-server --no-cache ./config-server

echo '*********************************************************************'
echo 'building discovery-server image..'
docker build -t omurka/microservice-world-discovery-server-eureka --no-cache ./discovery-server-eureka

echo '*********************************************************************'
echo 'building movie-catalog-service image..'
docker build -t omurka/microservice-world-movie-catalog-service --no-cache ./movie-catalog-service

echo '*********************************************************************'
echo 'building movie-info-service image..'
docker build -t omurka/microservice-world-movie-info-service --no-cache ./movie-info-service

echo '*********************************************************************'
echo 'building movie-rating-service image..'
docker build -t omurka/microservice-world-movie-rating-service --no-cache ./movie-rating-service

echo '*********************************************************************'
echo 'Docker images built.'