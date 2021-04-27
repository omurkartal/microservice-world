#!/bin/bash
cd $PWD
cd ../
echo $PWD

loggingLevel=""
if [ "$#" -eq  "0" ]
  then
    echo "logging level not supplied"
else
    loggingLevel="-Dorg.slf4j.simpleLogger.defaultLogLevel="$1
fi
echo ${loggingLevel}

echo '*********************************************************************'
echo "microservice-world-bom"
mvn -f microservice-world-bom/pom.xml clean install -U ${loggingLevel}

echo '*********************************************************************'
echo "microservice-world-parent"
mvn -f microservice-world-parent/pom.xml clean install -U ${loggingLevel}

echo '*********************************************************************'
echo "microservice-world-core-library"
mvn -f microservice-world-core-library/pom.xml clean install -U ${loggingLevel}

echo '*********************************************************************'
echo "api-gateway"
mvn -f api-gateway/pom.xml clean install -U -DskipTests ${loggingLevel}

echo '*********************************************************************'
echo "config-server"
mvn -f config-server/pom.xml clean install -U -DskipTests ${loggingLevel}

echo '*********************************************************************'
echo "discovery-server-eureka"
mvn -f discovery-server-eureka/pom.xml clean install -U -DskipTests ${loggingLevel}

echo '*********************************************************************'
echo "movie-catalog-service"
mvn -f movie-catalog-service/pom.xml clean install -U -DskipTests ${loggingLevel}

echo '*********************************************************************'
echo "movie-info-service"
mvn -f movie-info-service/pom.xml clean install -U -DskipTests ${loggingLevel}

echo '*********************************************************************'
echo "movie-rating-service"
mvn -f movie-rating-service/pom.xml clean install -U -DskipTests ${loggingLevel}

echo '*********************************************************************'
echo 'mvn-clean-install is completed.'
