#!/bin/bash
docker-compose down
docker rmi "employee-api"
mvn clean package -DskipTests
docker-compose up