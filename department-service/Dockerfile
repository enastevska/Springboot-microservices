FROM openjdk:11-jdk-alpine
MAINTAINER Elena Nastevska <elena.nastevska@hotmail.com>
ADD target/department-service-0.0.1-SNAPSHOT.jar deployment-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/deployment-service.jar"]