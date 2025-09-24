# ----------- Build Stage ------------
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# ----------- Production Stage ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose backend port (default Spring Boot 8080)
EXPOSE 8090

# Run the Spring Boot jar
ENTRYPOINT ["java","-jar","app.jar"]
