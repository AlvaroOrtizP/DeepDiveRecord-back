package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.application.usecase.FishingUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.FishingDetails;
import com.record.DeepDiveRecord.infrastructure.rest.controller.FishingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FishingControllerTest {

    @InjectMocks
    private FishingController fishingController;

    @Mock
    private FishingUseCase fishingUseCase;

    @BeforeEach
    void setUp() {
    }

    @Transactional
    @Test
    void testCreateFishing_Success() {
        // Simula la respuesta exitosa del caso de uso
        when(fishingUseCase.createFishing(any(InCreateFishing.class))).thenReturn(1);

        // Crea el objeto de entrada para el test
        InCreateFishing input = new InCreateFishing();

        // Ejecuta el método del controlador
        ResponseEntity<Integer> res = fishingController.createFishing(input);

        // Verifica los resultados
        assertAll("Verificaciones de respuesta",
                () -> assertEquals(1, res.getBody(), "Debe devolver el identificador del fishing creado"),
                () -> assertEquals(HttpStatus.CREATED, res.getStatusCode(), "Debe devolver el estado 201 indicando que fue creado")
        );
    }

    @Transactional
    @Test
    void testCreateFishing_Failed() {
        // Simula una excepción en el caso de uso
        when(fishingUseCase.createFishing(any(InCreateFishing.class))).thenThrow(new RuntimeException("Error en la creación"));

        // Crea el objeto de entrada para el test
        InCreateFishing input = new InCreateFishing();

        // Ejecuta el método del controlador
        ResponseEntity<Integer> res = fishingController.createFishing(input);

        // Verifica los resultados
        assertAll("Verificaciones de respuesta",
                () -> assertEquals(null, res.getBody(), "Debe devolver un cuerpo nulo"),
                () -> assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode(), "Debe devolver el estado 404 indicando que ocurrió un error")
        );
    }

    @Test
    void testGetFishingById_Success() {
        FishingDetails datails = new FishingDetails();
        datails.setId(1);
        // Simula la respuesta exitosa del caso de uso
        when(fishingUseCase.getFishingById(any(Integer.class))).thenReturn(datails);


        // Ejecuta el método del controlador
        ResponseEntity<FishingDetails> res = fishingController.getFishingById(1);

        // Verifica los resultados
        assertAll("Verificaciones de respuesta",
                () -> assertEquals(1, res.getBody().getId(), "Debe devolver el identificador del fishing obtenido"),
                () -> assertEquals(HttpStatus.OK, res.getStatusCode(), "Debe devolver el estado 200 indicando que fue encontrado")
        );
    }
    @Test
    void testGetFishingById_Failed() {
        when(fishingUseCase.getFishingById(any(Integer.class))).thenThrow(new RuntimeException("Error en la obtencion"));

        // Ejecuta el método del controlador
        ResponseEntity<FishingDetails> res = fishingController.getFishingById(1);

        // Verifica los resultados
        assertAll("Verificaciones de respuesta",
                () -> assertNull(res.getBody(), "Debe devolver null en el body"),
                () -> assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode(), "Debe devolver el estado 404 indicando que no fue encontrado")
        );
    }
}
