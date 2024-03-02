package com.record.DeepDiveRecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/windwu")
public class WindWuLoggerController {

    @Autowired
    private ResourceLoader resourceLoader;
    @GetMapping("/runPythonScript")
    public String runPythonScript() {
        try {
            // Ruta al script de Python
            String pythonScriptPath = "src/main/resources/static/windWuBot/WindWuLogger.py";

            // Obtener la ruta de salida para el script de Python desde el Classpath
            String resourcesPath = resourceLoader.getResource("classpath:").getFile().getAbsolutePath();
            // Argumentos para el script de Python (ruta del archivo JSON)

            System.out.println(resourcesPath);
            // Construir el comando para ejecutar el script de Python
            ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath, resourcesPath);

            // Redireccionar la salida estándar y el error
            pb.redirectErrorStream(true);

            // Iniciar el proceso
            Process process = pb.start();

            // Leer la salida del script de Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();

            // Devolver la salida del script de Python como respuesta
            return "Script de Python ejecutado con éxito.\nSalida:\n" + output.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error al ejecutar el script de Python: " + e.getMessage();
        }
    }
}