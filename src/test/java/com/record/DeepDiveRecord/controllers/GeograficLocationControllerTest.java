package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.application.usecase.GeographicalLocationUseCase;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import com.record.DeepDiveRecord.infrastructure.rest.controller.GeograficLocationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeograficLocationControllerTest {

    @InjectMocks
    private GeograficLocationController geograficLocationController;

    @Mock
    private GeographicalLocationUseCase geographicalLocationUseCase;

    @Mock
    private Logger logger;  // Puedes usar Mockito para verificar que se loguea correctamente

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllGeographicalLocation_Success() {
        // Simula una respuesta exitosa del caso de uso
        GeographicalLocationResponse location1 = new GeographicalLocationResponse();
        GeographicalLocationResponse location2 = new GeographicalLocationResponse();
        List<GeographicalLocationResponse> mockResponse = List.of(location1, location2);

        // Configura el comportamiento del caso de uso simulado
        when(geographicalLocationUseCase.getAllGeGeographicalLocation()).thenReturn(mockResponse);

        // Ejecuta el método del controlador
        List<GeographicalLocationResponse> res = geograficLocationController.getAllGeGeographicalLocation();

        // Verifica los resultados
        assertEquals(2, res.size(), "Debe devolver la cantidad correcta de ubicaciones geográficas");
        assertEquals(mockResponse, res, "Debe devolver la misma lista que el caso de uso");
    }

    @Test
    void testGetAllGeographicalLocation_Empty() {
        // Simula una respuesta vacía del caso de uso
        when(geographicalLocationUseCase.getAllGeGeographicalLocation()).thenReturn(Collections.emptyList());

        // Ejecuta el método del controlador
        List<GeographicalLocationResponse> res = geograficLocationController.getAllGeGeographicalLocation();

        // Verifica los resultados
        assertEquals(0, res.size(), "Debe devolver una lista vacía cuando no hay ubicaciones geográficas");
    }

    @Test
    void testGetAllGeographicalLocation_Exception() {
        // Simula una excepción en el caso de uso
        when(geographicalLocationUseCase.getAllGeGeographicalLocation()).thenThrow(new RuntimeException("Error al obtener las ubicaciones geográficas"));

        // Ejecuta el método del controlador esperando la excepción
        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            geograficLocationController.getAllGeGeographicalLocation();
        });

        // Verifica el mensaje de la excepción
        assertEquals("Error al obtener las ubicaciones geográficas", exception.getMessage(), "Debe lanzar la excepción correcta con el mensaje esperado");
    }
}