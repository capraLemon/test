FROM openjdk:11-jdk-slim

COPY src /usr/src/checkup/src
COPY pom.xml /usr/src/checkup
COPY target /usr/src/checkup/target

WORKDIR /usr/src/checkup

CMD java -DsettingsDir=target/classes/ -jar target/*SNAPSHOT.jar
