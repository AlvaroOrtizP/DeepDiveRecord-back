package com.record.DeepDiveRecord;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeepDiveRecordApplication {

	public static void main(String[] args) {
		// Detectar si estamos dentro de Docker o no
		String dotenvDirectory = System.getenv("DOTENV_DIR") != null ? System.getenv("DOTENV_DIR") : ".";

		// Cargar las variables desde el archivo .env en la ruta correcta (solo en local)
		Dotenv dotenv = Dotenv.configure()
				.directory(dotenvDirectory) // Usa la ruta correspondiente
				.load();

		// Si estamos en local, podemos cargar el archivo .env manualmente
		String dbUsername = dotenv.get("DB_USERNAME");
		String dbPassword = dotenv.get("DB_PASSWORD");
		String dbIp = dotenv.get("DB_IP");
		String dbName = dotenv.get("DB_NAME");

		String mailUsername = dotenv.get("MAIL_USERNAME");
		String mailPassword = dotenv.get("MAIL_PASSWORD");

		// Si estamos en Docker, las variables de entorno ya estarán disponibles
		// y no será necesario hacer nada adicional

		// Solo usaremos System.setProperty si las variables no están en el entorno de Docker
		if (dbUsername != null) {
			System.setProperty("DB_USERNAME", dbUsername);
		}
		if (dbPassword != null) {
			System.setProperty("DB_PASSWORD", dbPassword);
		}
		if (dbIp != null) {
			System.setProperty("DB_IP", dbIp);
		}
		if (dbName != null) {
			System.setProperty("DB_NAME", dbName);
		}
		if (mailUsername != null) {
			System.setProperty("MAIL_USERNAME", mailUsername);
		}
		if (mailPassword != null) {
			System.setProperty("MAIL_PASSWORD", mailPassword);
		}

		SpringApplication.run(DeepDiveRecordApplication.class, args);
	}
}
