# ================= BUILD =================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY codereviewer/pom.xml .
COPY codereviewer/src ./src

RUN mvn clean package -DskipTests


# ================= RUN =================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
