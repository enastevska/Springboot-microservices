FROM openjdk:11-jdk-alpine
MAINTAINER Elena Nastevska <elena.nastevska@hotmail.com>
ADD target/employee-service-0.0.1-SNAPSHOT.jar employee-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/employee-service.jar"]