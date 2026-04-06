# ================= BUILD =================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests


# ================= RUN =================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]