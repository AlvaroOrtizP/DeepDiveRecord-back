# Stage 1: Construcción
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copia todos los archivos del proyecto en el contenedor
COPY . .

# Ejecuta el build y empaqueta el proyecto, sin ejecutar las pruebas
RUN mvn clean package -DskipTests

# Verifica si el archivo JAR existe
RUN echo "Buscando archivo JAR:" && \
    find /app/target/ -name "*.jar"

# Stage 2: Ejecución
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Definir un argumento para recibir el nombre del archivo JAR
ARG JAR_FILE

# Copia el archivo JAR generado en el primer stage
COPY --from=build /app/target/${JAR_FILE} app.jar  # Usar la variable JAR_FILE

EXPOSE 8080

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
