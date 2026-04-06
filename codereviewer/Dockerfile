# ================= BUILD STAGE =================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy all project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests


# ================= RUN STAGE =================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the generated jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render will override with PORT env)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
