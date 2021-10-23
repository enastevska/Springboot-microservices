FROM openjdk:11-jdk-alpine
MAINTAINER Elena Nastevska <elena.nastevska@hotmail.com>
ADD target/service-registry-0.0.1-SNAPSHOT.jar service-registry.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/service-registry.jar"]