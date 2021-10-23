FROM openjdk:11-jdk-alpine
MAINTAINER Elena Nastevska <elena.nastevska@hotmail.com>
ADD target/cloud-gateway-0.0.1-SNAPSHOT.jar cloud-gateway.jar
EXPOSE 9191
ENTRYPOINT ["java", "-jar", "/cloud-gateway.jar"]