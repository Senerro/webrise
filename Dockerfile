FROM eclipse-temurin:17-jdk-alpine
LABEL authors="Павел"
WORKDIR /app
COPY target/assigment-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

