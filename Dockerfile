# FROM openjdk:17-jdk-alpine
#
# WORKDIR /app
#
# COPY target/bug-tracker-API-0.0.1.jar /app/bug-tracker-API-0.0.1.jar
#
# EXPOSE 8080
#
# ENTRYPOINT ["java", "-jar", "/app/bug-tracker-API-0.0.1.jar"]


FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

# Create the final image
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/bug-tracker-API-0.0.1.jar /app/bug-tracker-API-0.0.1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/bug-tracker-API-0.0.1.jar"]