#
# Build stage
#
FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /home/app
COPY pom.xml .
RUN mvn dependency:resolve

COPY src/ /home/app/src/
RUN mvn package

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/backend-challenge.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/backend-challenge.jar"]