# Stage 1: Construcción
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copia todos los archivos del proyecto en el contenedor
COPY . .

# Ejecuta el build y empaqueta el proyecto, ejecutando las pruebas
RUN mvn clean package

# Stage 2: Ejecución
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Definir argumentos para las variables de entorno
ARG JAR_FILE
ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD
ARG MAIL_USERNAME
ARG MAIL_PASSWORD

# Copiar el archivo JAR generado en el primer stage
COPY --from=build /app/${JAR_FILE} app.jar

# Definir variables de entorno en el contenedor
ENV DB_NAME=${DB_NAME}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV MAIL_USERNAME=${MAIL_USERNAME}
ENV MAIL_PASSWORD=${MAIL_PASSWORD}

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
