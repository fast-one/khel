#!/bin/bash
# Script to Start Spring Boot with maven in production mode.
#

mvn spring-boot:run -Drun.jvmArguments="-XX:MaxPermSize=128m -Dspring.profiles.active=prod" 