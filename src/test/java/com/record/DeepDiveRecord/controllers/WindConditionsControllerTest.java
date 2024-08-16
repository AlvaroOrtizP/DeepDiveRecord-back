package com.record.DeepDiveRecord.controllers;
import com.record.DeepDiveRecord.application.usecase.WindConditionsUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;
import com.record.DeepDiveRecord.infrastructure.rest.controller.WindConditionsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class WindConditionsControllerTest {

    @InjectMocks
    private WindConditionsController windConditionsController;

    @Mock
    private WindConditionsUseCase windConditionsUseCase;

    @Mock
    private Logger logger;  // Puedes usar Mockito para verificar que se loguea correctamente

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetDataWeek_Success() {
        // Simula una respuesta exitosa del caso de uso
        OutGetDataList mockResponse = new OutGetDataList();
        // Configura el comportamiento del caso de uso simulado
        when(windConditionsUseCase.getDeepDiveDataByDays(any(InGetDataWeek.class))).thenReturn(mockResponse);

        // Crea el input simulado
        InGetDataWeek input = new InGetDataWeek();

        // Ejecuta el método del controlador
        ResponseEntity<OutGetDataList> res = windConditionsController.getDataWeek(input);

        // Verifica los resultados
        assertEquals(HttpStatus.OK, res.getStatusCode(), "Debe devolver el estado HTTP 200 OK");
        assertEquals(mockResponse, res.getBody(), "Debe devolver la respuesta correcta del caso de uso");
    }

    @Test
    void testGetDataWeek_EmptyResponse() {
        // Simula una respuesta vacía del caso de uso
        OutGetDataList emptyResponse = new OutGetDataList();
        when(windConditionsUseCase.getDeepDiveDataByDays(any(InGetDataWeek.class))).thenReturn(emptyResponse);

        // Crea el input simulado
        InGetDataWeek input = new InGetDataWeek();

        // Ejecuta el método del controlador
        ResponseEntity<OutGetDataList> res = windConditionsController.getDataWeek(input);

        // Verifica los resultados
        assertEquals(HttpStatus.OK, res.getStatusCode(), "Debe devolver el estado HTTP 200 OK");
        assertEquals(emptyResponse, res.getBody(), "Debe devolver una respuesta vacía correctamente");
    }

    @Test
    void testGetDataWeek_Exception() {
        // Simula una excepción lanzada por el caso de uso
        when(windConditionsUseCase.getDeepDiveDataByDays(any(InGetDataWeek.class))).thenThrow(new RuntimeException("Error al obtener los datos"));

        // Crea el input simulado
        InGetDataWeek input = new InGetDataWeek();

        // Ejecuta el método del controlador esperando la excepción
        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            windConditionsController.getDataWeek(input);
        });

        // Verifica el mensaje de la excepción
        assertEquals("Error al obtener los datos", exception.getMessage(), "Debe lanzar la excepción correcta con el mensaje esperado");
    }
}