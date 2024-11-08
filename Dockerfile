# Stage 1: Construcción
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Ejecución

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/tu-proyecto.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]