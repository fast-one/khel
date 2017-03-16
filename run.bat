@echo off
rem Script to Start Spring Boot with maven in production mode.
rem

mvn spring-boot:run -Drun.jvmArguments="-XX:MaxPermSize=128m -Dspring.profiles.active=prod" 