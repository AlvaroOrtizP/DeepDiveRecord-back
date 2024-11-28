package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.HistoricUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("/gestor")
@RestController
public class HistoricController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HistoricController.class);
    @Autowired
    private HistoricUseCase gestorUseCase;


    @GetMapping("/")
    public ResponseEntity<?> security() {

        LOGGER.info("Comienza el metodo security");
        int res = 0;
        try {
            res = gestorUseCase.securityData();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
//Numero total de registros: 2265
        return new ResponseEntity<>("Numero total de registros: " + res, HttpStatus.OK);
    }

    @PutMapping("/")
    public  ResponseEntity<?> saveLastSecutiry(){
        LOGGER.info("Comienza el metodo saveLastSecutiry");
        int res = 0;
        try {
            res = gestorUseCase.saveLastSecutiry();
        }catch (Exception e){
            System.out.println("error " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Numero total de registros: " + res, HttpStatus.OK);
    }
    @PostMapping("/upload")
    public ResponseEntity<?> processExcel(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Comienza el método processExcel");

        if (file.isEmpty()) {
            return new ResponseEntity<>("El archivo está vacío", HttpStatus.BAD_REQUEST);
        }

        try {
            // Guardar temporalmente el archivo en el sistema
            File tempFile = File.createTempFile("uploaded-", file.getOriginalFilename());
            file.transferTo(tempFile);

            LOGGER.info("Archivo subido exitosamente: " + tempFile.getAbsolutePath());

            // Reutilizar la lógica existente para procesar el archivo
            int res = gestorUseCase.saveHistoric(tempFile);

            // Eliminar el archivo temporal después de procesarlo
            boolean deleted = tempFile.delete();
            if (!deleted) {
                LOGGER.warn("No se pudo eliminar el archivo temporal: " + tempFile.getAbsolutePath());
            }

            return new ResponseEntity<>("Archivo procesado. Registros guardados: " + res, HttpStatus.OK);

        } catch (IOException e) {
            LOGGER.error("Error al procesar el archivo: " + e.getMessage(), e);
            return new ResponseEntity<>("Error al procesar el archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   // @PostMapping("/upload1")
    /*public ResponseEntity<?> processExcel1(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Comienza el método processExcel");

        if (file.isEmpty()) {
            return new ResponseEntity<>("El archivo está vacío", HttpStatus.BAD_REQUEST);
        }

        try {
            // Ruta de la carpeta "resources/output"
            Path outputDir = Paths.get("src/main/resources/output");

            // Crear la carpeta si no existe
            if (!Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
                LOGGER.info("Directorio creado: " + outputDir.toAbsolutePath());
            }

            // Crear un archivo en la carpeta de salida
            Path outputPath = outputDir.resolve(file.getOriginalFilename());
            File outputFile = outputPath.toFile();

            // Transferir contenido del archivo recibido al archivo destino
            file.transferTo(outputFile);

            LOGGER.info("Archivo guardado en: " + outputPath.toAbsolutePath());

            // Reutilizar la lógica existente para procesar el archivo
            int res = gestorUseCase.saveHistoric(outputFile);

            return new ResponseEntity<>("Archivo procesado. Registros guardados: " + res, HttpStatus.OK);

        } catch (IOException e) {
            LOGGER.error("Error al procesar el archivo: " + e.getMessage(), e);
            return new ResponseEntity<>("Error al procesar el archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}