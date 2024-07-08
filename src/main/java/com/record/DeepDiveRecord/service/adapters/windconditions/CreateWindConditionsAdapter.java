package com.record.DeepDiveRecord.service.adapters.windconditions;

import com.record.DeepDiveRecord.core.model.windconditions.InForecast;
import com.record.DeepDiveRecord.core.model.windconditions.OutForecast;
import com.record.DeepDiveRecord.core.ports.windconditions.CreateWindConditionsPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CreateWindConditionsAdapter implements CreateWindConditionsPort {
    private static final Logger logger = LoggerFactory.getLogger(CreateWindConditionsAdapter.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public OutForecast runPythonScript(InForecast deepDiveLogger) {

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

            // Ahora agregar la tarea para ejecutar GuardarDatos.py
            completionService.submit(() -> String.valueOf(callPython("Main.py", webWeatherScraperMainPath, webWeatherScraperMainPath)));

            // Esperar a que la tarea de GuardarDatos.py termine
            Future<String> guardarDatosFuture = completionService.take();
            return response(guardarDatosFuture.get());

        } catch (IOException | InterruptedException | ExecutionException e) {
            OutForecast deepDiveResponse = new OutForecast();
            List<String> errors = new ArrayList<>();
            errors.add("Error al ejecutar el script de Python: " + e.getMessage());
            deepDiveResponse.setErros(errors);
            return deepDiveResponse;
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

    private String callPython(String namePythonProgram, String pythonPath, String secondParameter) throws IOException, InterruptedException {
        // Construir el comando para ejecutar el script de Python con los parámetros
        ProcessBuilder pb = null;

        pb = new ProcessBuilder("python", pythonPath + namePythonProgram, secondParameter);


        // Redireccionar la salida estándar y el error
        pb.redirectErrorStream(true);

        // ProcessBuilder pb = new ProcessBuilder("python", "Main.py", deepDiveLogger.getIdWindwuru(), deepDiveLogger.getIdAemet());
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

    private OutForecast response(String guardarDatosFuture) {
        if (guardarDatosFuture.equals("OK")) {
            return new OutForecast(null);
        }

        String patron = "NOK:";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(guardarDatosFuture);

        OutForecast deepDiveResponse = new OutForecast();
        List<String> erros = new ArrayList<>();
        // Iterar sobre las coincidencias y separar el texto en Strings
        int inicio = 0;
        while (matcher.find()) {
            String resultado = guardarDatosFuture.substring(inicio, matcher.start());
            logger.error("ERROR EN LA CARGA DE DATOS (SCRIPT) " + resultado.trim());
            erros.add(resultado.trim());
            inicio = matcher.start();
        }
        deepDiveResponse.setErros(erros);

        return deepDiveResponse;
    }


}