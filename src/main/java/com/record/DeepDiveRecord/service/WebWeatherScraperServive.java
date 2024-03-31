package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.dto.request.WebWeatherScraperRequest;
import com.record.DeepDiveRecord.dto.responses.WebWeatherScraperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WebWeatherScraperServive {
    @Autowired
    private ResourceLoader resourceLoader;

    public WebWeatherScraperResponse runPythonScript(WebWeatherScraperRequest webWeatherScraperDto) {
        try {
            //OBTENER LA RUTA DE EJECUCION DEL PROYECTO SPRING
            String projectSpringPath = getAbsolutePath();

            //OBTENER LA RUTA DEL PROYECTO WebWeatherScraper
            String webWeatherScraperMainPath = getPythonMainPath(projectSpringPath);

            //Obtenemos la ruta de resources
            String resourcesPath = getResourcePath(projectSpringPath);

            // Ejecutar los procesos en hilos separados
            ExecutorService executor = Executors.newFixedThreadPool(3);
            CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

            completionService.submit(() -> String.valueOf(callPython("ObtenerDatos/WindWuLogger.py", webWeatherScraperMainPath, webWeatherScraperDto.getIdWindwuru(), webWeatherScraperMainPath)));
            completionService.submit(() -> String.valueOf(callPython("ObtenerDatos/TemperaturaLogger.py", webWeatherScraperMainPath, webWeatherScraperDto.getIdAemet(), webWeatherScraperMainPath)));
            completionService.submit(() -> String.valueOf(callPython("ObtenerDatos/ObtenerDireccionViento.py", webWeatherScraperMainPath, webWeatherScraperDto.getLugar(), webWeatherScraperMainPath)));

            // Esperar a que todos los procesos terminen
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                Future<String> future = completionService.take();
                output.append(future.get()).append("\n");
            }
            // Ahora agregar la tarea para ejecutar GuardarDatos.py
            completionService.submit(() -> String.valueOf(callPython("GuardarDatos.py", webWeatherScraperMainPath, webWeatherScraperDto.getIdWindwuru(),  webWeatherScraperMainPath)));

            // Esperar a que la tarea de GuardarDatos.py termine
            Future<String> guardarDatosFuture = completionService.take();

            return response(guardarDatosFuture.get());

        } catch (IOException | InterruptedException | ExecutionException e) {
            WebWeatherScraperResponse webWeatherScraperResponse = new WebWeatherScraperResponse();
            List<String> errors = new ArrayList<>();
            errors.add("Error al ejecutar el script de Python: " + e.getMessage());
            webWeatherScraperResponse.setErros(errors);
            return webWeatherScraperResponse;
        }
    }

    /**
     * Metodo que obtiene la ruta absoluta del proyecto DeepDiveRecord
     *
     * @return C:\....\DeepDiveRecord\
     * @throws IOException
     */
    private String getAbsolutePath() throws IOException {
        String resourcesPath = resourceLoader.getResource("classpath:").getFile().getAbsolutePath();
        return resourcesPath.substring(0, resourcesPath.indexOf("target\\classes"));
    }

    /**
     * Devuelve la ruta resources de este proyecto
     *
     * @param projectSpringPath
     * @return C:/.../DeepDiveRecord/src/main/resources
     */
    private String getResourcePath(String projectSpringPath) {
        String resourcesPath = projectSpringPath.replace("\\", "/");
        resourcesPath = resourcesPath + "src/main/resources/";
        return resourcesPath;
    }

    /**
     * Obtiene la ruta del archivo Main.py el cual se encuentra en el proyecto WebWeatherScraper
     * mediante la localizacion de nuestro proyecto
     *
     * @param projectSpringPath
     * @return C:\....\WebWeatherScraper\Main.py
     */
    private String getPythonMainPath(String projectSpringPath) {
        return projectSpringPath.replace("DeepDiveRecord", "WebWeatherScraper");
    }

    private String  callPython(String namePythonProgram, String pythonPath, String firstParameter, String secondParameter) throws IOException, InterruptedException {
        // Construir el comando para ejecutar el script de Python con los parámetros
        ProcessBuilder pb = new ProcessBuilder("python", pythonPath + namePythonProgram, firstParameter, secondParameter);

        // Redireccionar la salida estándar y el error
        pb.redirectErrorStream(true);

        // ProcessBuilder pb = new ProcessBuilder("python", "Main.py", webWeatherScraperDto.getIdWindwuru(), webWeatherScraperDto.getIdAemet());
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
        return output.toString();
    }

    private WebWeatherScraperResponse response(String guardarDatosFuture){
        if(guardarDatosFuture.equals("OK")) {
            return new WebWeatherScraperResponse(null);
        }

        String patron = "NOK:";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(guardarDatosFuture);

        WebWeatherScraperResponse webWeatherScraperResponse = new WebWeatherScraperResponse();
        List<String> erros = new ArrayList<>();
        // Iterar sobre las coincidencias y separar el texto en Strings
        int inicio = 0;
        while (matcher.find()) {
            String resultado = guardarDatosFuture.substring(inicio, matcher.start());

            erros.add(resultado.trim());
            inicio = matcher.start();
        }
        webWeatherScraperResponse.setErros(erros);

        return webWeatherScraperResponse;
    }
}