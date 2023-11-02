FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy your Spring Boot JAR file into the container
COPY target/bug-tracker-API-0.0.1.jar /app/bug-tracker-API-0.0.1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/bug-tracker-API-0.0.1.jar"]