#### Stage 1: Build the application
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package spring-boot:repackage
#### State 2: Run application
FROM openjdk:8-jdk-alpine
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/*.jar jpay.jar
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/application.properties application.properties
ENTRYPOINT ["java","-jar","/jpay.jar"]