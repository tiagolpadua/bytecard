#!/usr/bin/env bash

export JAVA_HOME=/Users/tiagopadua/dev/jdk-17.0.6.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

#./mvnw -Dspring-boot.run.profiles=dev clean spring-boot:run
./mvnw -Dtest=ApiApplicationTests -Dspring.profiles.active=test test