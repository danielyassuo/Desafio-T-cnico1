FROM maven:3.8-eclipse-temurin-11-alpine AS build

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:11-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 1450

CMD ["java", "-jar", "/app/app.jar"]