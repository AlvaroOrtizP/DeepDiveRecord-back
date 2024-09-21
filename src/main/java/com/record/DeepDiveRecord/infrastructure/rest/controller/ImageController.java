package com.record.DeepDiveRecord.infrastructure.rest.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Value("${upload.path}")
    private String uploadDir;

    /*@PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Archivo vacío");
        }

        try {
            // Guardar en src/main/resources/static/images/
            File dest = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(dest);
            return ResponseEntity.ok("Imagen subida con éxito: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        }
    }*/

    @GetMapping("/{filename:.+}")
    public ResponseEntity<byte[]> serveImage(@PathVariable String filename) {
        LOGGER.info("Obtener imagen " + filename);
        filename = filename.replace("_", "/");

        try {
            Resource imgFile = new ClassPathResource("images/" + filename);
            LOGGER.info("Obtener imgFile " + imgFile);
            InputStream in = imgFile.getInputStream();
            byte[] imageBytes = FileCopyUtils.copyToByteArray(in);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(imageBytes.length);

            // Determinar el tipo de contenido basado en la extensión del archivo
            String contentType = determineContentType(filename);
            headers.setContentType(MediaType.valueOf(contentType));

            return ResponseEntity.ok().headers(headers).body(imageBytes);
        } catch (IOException e) {
            LOGGER.error("Error al obtener imagen", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private String determineContentType(String filename) {
        if (filename.endsWith(".svg")) {
            return "image/svg+xml";
        } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (filename.endsWith(".png")) {
            return "image/png";
        } else {
            return "application/octet-stream"; // Tipo genérico si el archivo no es reconocido
        }
    }
}
