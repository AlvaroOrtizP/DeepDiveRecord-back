package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.application.usecase.DiveDayUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.rest.controller.DiveDayController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiveDayControllerTest {
    @InjectMocks
    private DiveDayController diveDayController;

    @Mock
    private DiveDayUseCase diveDayUseCase;

    @BeforeEach
    void setUp() {
    }

    @Transactional
    @Test
    void testCreateDiveDay_Success() {
        // Simula la respuesta del mapeo y del guardado de DiveDay
        DiveDayEntity diveDayEntity = new DiveDayEntity();
        diveDayEntity.setDiveDayId(1);

        // Simula la creación de DiveDay
        when(diveDayUseCase.createDiveDay(any())).thenReturn(diveDayEntity.getDiveDayId());

        ResponseEntity<Integer> res = diveDayController.createDiveDay(getInCreateDailyDiving());

        assertAll("Verificaciones de respuesta",
                () -> assertEquals(1, res.getBody(), "Devuelve el identificador del DiveDay creado"),
                () -> assertEquals(HttpStatus.CREATED, res.getStatusCode(), "Devuelve el estado 201 indicando que fue creado un registro")
        );

        verify(diveDayUseCase).createDiveDay(any());
    }

    @Test
    void testGetDiveDayById_Success() {
        // Simula la petición de un DiveDay exitoso
        DiveDayDetailsResponse diveDayResponse = new DiveDayDetailsResponse();
        diveDayResponse.setDiveDayId(1);

        // Configura el comportamiento simulado del caso de uso
        when(diveDayUseCase.findDiveDayById(any())).thenReturn(diveDayResponse);

        // Llama al método del controlador
        ResponseEntity<DiveDayDetailsResponse> res = diveDayController.getDiveDayById(1);

        // Verifica la respuesta
        assertAll("Verificaciones de respuesta",
                () -> assertEquals(1, res.getBody().getDiveDayId(), "Devuelve el DiveDay correcto"),
                () -> assertEquals(HttpStatus.OK, res.getStatusCode(), "Devuelve el estado 200 indicando éxito")
        );
    }

    @Test
    void testGetDiveDayById_NotFound() {
        // Simula un caso en el que no se encuentra el DiveDay
        when(diveDayUseCase.findDiveDayById(any())).thenReturn(null);

        // Llama al método del controlador
        ResponseEntity<DiveDayDetailsResponse> res = diveDayController.getDiveDayById(1);

        // Verifica que la respuesta sea 404 NOT FOUND
        assertAll("Verificaciones de respuesta",
                () -> assertEquals(null, res.getBody(), "El cuerpo de la respuesta debe ser nulo"),
                () -> assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode(), "Devuelve el estado 404 indicando que no se encontró el registro")
        );
    }

    @Test
    void testCreateDiveDay_Failure() {
        // Simula una excepción al crear un DiveDay
        when(diveDayUseCase.createDiveDay(any())).thenThrow(new RuntimeException("Error al crear DiveDay"));

        // Llama al método del controlador esperando una excepción
        ResponseEntity<Integer> res = diveDayController.createDiveDay(getInCreateDailyDiving());

        // Verifica la respuesta en caso de fallo
        assertAll("Verificaciones de respuesta",
                () -> assertEquals(null, res.getBody(), "El cuerpo de la respuesta debe ser nulo en caso de error"),
                () -> assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode(), "Devuelve el estado 404 indicando un error en la creación")
        );
    }

    @Test
    void testGetFishingDays_Success() {
        // Arrange
        String zona = "Pacific";
        int page = 0;
        int size = 10;
        String sortDirection = "asc";
        String sortBy = "fecha";

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
        DiveDayResponse diveDayResponse = new DiveDayResponse(); // Crear una instancia de DiveDayEntity
        Page<DiveDayResponse> diveDayPage = new PageImpl<>(Collections.singletonList(diveDayResponse));

        when(diveDayUseCase.findByFilters(zona, pageable)).thenReturn(diveDayPage);

        // Act
        ResponseEntity<Page<DiveDayResponse>> res = diveDayController.getFishingDays(page, size, zona, sortDirection, sortBy);

        // Assert
        assertAll("Verificaciones de respuesta",
                () -> assertNotNull(res.getBody(), "La respuesta no debería ser nula"),
                () -> assertEquals(1, res.getBody().getTotalElements(), "Debería devolver un elemento"),
                () -> assertEquals(HttpStatus.OK, res.getStatusCode(), "Devuelve el estado 200 indicando que la solicitud fue exitosa")
        );

        verify(diveDayUseCase).findByFilters(zona, pageable);
    }
    @Test
    void testGetFishingDays_NoResults() {
        // Arrange
        String zona = "Atlantic";
        int page = 0;
        int size = 10;
        String sortDirection = "asc";
        String sortBy = "fecha";

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
        Page<DiveDayResponse> emptyPage = new PageImpl<>(Collections.emptyList());

        when(diveDayUseCase.findByFilters(zona, pageable)).thenReturn(emptyPage);

        // Act
        ResponseEntity<Page<DiveDayResponse>> res = diveDayController.getFishingDays(page, size, zona, sortDirection, sortBy);

        // Assert
        assertAll("Verificaciones de respuesta",
                () -> assertNotNull(res.getBody(), "La respuesta no debería ser nula"),
                () -> assertEquals(0, res.getBody().getTotalElements(), "Debería devolver cero elementos"),
                () -> assertEquals(HttpStatus.OK, res.getStatusCode(), "Devuelve el estado 200 indicando que la solicitud fue exitosa")
        );

        verify(diveDayUseCase).findByFilters(zona, pageable);
    }

    private InCreateDailyDiving getInCreateDailyDiving() {
        InCreateDailyDiving inCreateDailyDiving = new InCreateDailyDiving();
        inCreateDailyDiving.setIdGeograficLocation(487006);
        inCreateDailyDiving.setDay("1");
        inCreateDailyDiving.setName("Isla");
        inCreateDailyDiving.setEnd("16:15");
        inCreateDailyDiving.setBeginning("15:00");
        inCreateDailyDiving.setSite("487006");
        inCreateDailyDiving.setAssessment(2);
        inCreateDailyDiving.setIdGeograficLocation(1);
        inCreateDailyDiving.setMonth("2");
        inCreateDailyDiving.setYear("2024");
        inCreateDailyDiving.setName("Prueba");
        inCreateDailyDiving.setJellyfish(0);
        inCreateDailyDiving.setVisibility(1);
        inCreateDailyDiving.setSeaBackground(1);
        inCreateDailyDiving.setFishGrass(1);
        inCreateDailyDiving.setPresencePlastic(1);
        return inCreateDailyDiving;
    }
}
