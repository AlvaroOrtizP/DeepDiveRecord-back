# Stage 1: Construcción
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copia todos los archivos del proyecto en el contenedor
COPY . .

# Ejecuta el build y empaqueta el proyecto, sin ejecutar las pruebas
RUN mvn clean package -DskipTests

# Stage 2: Ejecución
FROM openjdk:17-jdk-alpine  # Imagen ligera de OpenJDK 17

WORKDIR /app

# Copia el archivo JAR generado en el primer stage
COPY --from=build /app/target/tu-proyecto.jar app.jar  # Reemplaza 'tu-proyecto.jar' con el nombre correcto de tu archivo JAR

EXPOSE 8080  # Exponemos el puerto 8080 donde la aplicación escuchará

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
