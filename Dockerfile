# Dockerfile for Spring Boot project
FROM maven:3.9.9-eclipse-temurin-23-alpine AS build
LABEL authors="devaleriofrancesco"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jdk-alpine
VOLUME /tmp
COPY --from=build /app/target/*.jar app.jar
# Copy uploads folder into /uploads
ADD uploads /uploads
ENTRYPOINT ["java","-jar","/app.jar"]