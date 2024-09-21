package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.infrastructure.rest.controller.ImageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageController imageController;

    @Value("${upload.path}")
    private String uploadDir;

    @BeforeEach
    public void setup() throws IOException {
        // Crear el directorio de destino si no existe
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
    }

    //@Test
    void testUploadImage() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "Test Image Content".getBytes()
        );

        mockMvc.perform(multipart("/api/images/upload")
                        .file(mockFile))
                .andExpect(status().isOk())
                .andExpect(content().string("Imagen subida con éxito: test.jpg"));
    }

    //@Test
    void testUploadEmptyImage() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "",
                MediaType.IMAGE_JPEG_VALUE,
                "".getBytes()
        );

        mockMvc.perform(multipart("/api/images/upload")
                        .file(mockFile))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Archivo vacío"));
    }

    //@Test
    void testServeImage() throws Exception {
        String filename = "fish/1.png";
        Resource resource = new ClassPathResource("images/" + filename);

        if (!resource.exists()) {
            throw new RuntimeException("El recurso de prueba no existe: " + filename);
        }

        byte[] imageBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());

        //when(imageController.serveImage(filename)).thenCallRealMethod();

        mockMvc.perform(get("/api/images/{filename}", filename))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(content().bytes(imageBytes));
    }

    //Test
    void testServeNonExistingImage() throws Exception {
        String filename = "non_existing.jpg";

        when(imageController.serveImage(filename)).thenReturn(
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        );

        mockMvc.perform(get("/api/images/{filename}", filename))
                .andExpect(status().isNotFound());
    }
}
