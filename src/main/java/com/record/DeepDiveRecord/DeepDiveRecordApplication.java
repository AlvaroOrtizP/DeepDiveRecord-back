package com.record.DeepDiveRecord;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeepDiveRecordApplication {

	public static void main(String[] args) {
		// Detectar si estamos dentro de Docker o no
		String dotenvDirectory = System.getenv("DOTENV_DIR") != null ? System.getenv("DOTENV_DIR") : ".";

		// Cargar las variables desde el archivo .env en la ruta correcta
		Dotenv dotenv = Dotenv.configure()
				.directory(dotenvDirectory) // Usa la ruta correspondiente
				.load();

		// Configurar variables de entorno manualmente
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("DB_IP", dotenv.get("DB_IP"));
		System.setProperty("DB_NAME", dotenv.get("DB_NAME"));

		System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
		System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));

		SpringApplication.run(DeepDiveRecordApplication.class, args);
	}

}
