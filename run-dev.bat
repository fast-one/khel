@echo off
rem  Script to Start Spring Boot with maven in debug mode.
rem
rem If you have JRebel set the environment variable for JREBEL_AGENT_ARG to enable it
rem  Example line for .bash_profile (OSX) or .bashrc (*nix):
rem  export JREBEL_AGENT_ARG="-Drun.agent=\"/path/to/jrebel/jrebel.jar\""

mvn spring-boot:run -Drun.jvmArguments="-XX:MaxPermSize=128m -Dspring.profiles.active=dev -Dspring.config.location=classpath:/local/ -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5007" %JREBEL_AGENT_ARG%