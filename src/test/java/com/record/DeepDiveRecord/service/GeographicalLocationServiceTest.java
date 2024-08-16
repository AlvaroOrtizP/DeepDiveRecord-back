package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.application.service.GeographicalLocationService;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class GeographicalLocationServiceTest {
    @Mock
    private GeographicalLocationPort geographicalLocationRepositoryPort;

    @Mock
    private GeograficLocationMapper geograficLocationMapper;

    @InjectMocks
    private GeographicalLocationService geographicalLocationService;

    private List<GeographicalLocationEntity> geographicalLocationEntityList;
    private List<GeographicalLocationResponse> geographicalLocationResponseList;

    @BeforeEach
    void setUp() {
        // Inicializar listas de prueba
        geographicalLocationEntityList = new ArrayList<>();
        geographicalLocationResponseList = new ArrayList<>();

        // Crear y agregar GeographicalLocationEntity de prueba
        GeographicalLocationEntity entity1 = new GeographicalLocationEntity();
        entity1.setId(1);
        entity1.setName("Location 1");
        geographicalLocationEntityList.add(entity1);

        GeographicalLocationEntity entity2 = new GeographicalLocationEntity();
        entity2.setId(2);
        entity2.setName("Location 2");
        geographicalLocationEntityList.add(entity2);

        // Crear y agregar GeographicalLocationResponse de prueba
        GeographicalLocationResponse response1 = new GeographicalLocationResponse();
        response1.setId(1);
        response1.setName("Location 1");
        geographicalLocationResponseList.add(response1);

        GeographicalLocationResponse response2 = new GeographicalLocationResponse();
        response2.setId(2);
        response2.setName("Location 2");
        geographicalLocationResponseList.add(response2);
    }

    @Test
    void testGetAllGeGeographicalLocation_Success() {
        // Configurar mocks para devolver la lista de entidades y respuestas
        when(geographicalLocationRepositoryPort.getAllGeGeographicalLocation()).thenReturn(geographicalLocationEntityList);
        when(geograficLocationMapper.mapFromEntity(geographicalLocationEntityList.get(0))).thenReturn(geographicalLocationResponseList.get(0));
        when(geograficLocationMapper.mapFromEntity(geographicalLocationEntityList.get(1))).thenReturn(geographicalLocationResponseList.get(1));

        // Ejecutar el método a probar
        List<GeographicalLocationResponse> result = geographicalLocationService.getAllGeGeographicalLocation();

        // Verificar el resultado y las interacciones
        assertEquals(2, result.size(), "El tamaño de la lista de resultados debería ser 2");
        assertEquals(geographicalLocationResponseList, result, "La lista de respuestas debería coincidir con la lista de prueba");
        verify(geographicalLocationRepositoryPort, times(1)).getAllGeGeographicalLocation();
        verify(geograficLocationMapper, times(1)).mapFromEntity(geographicalLocationEntityList.get(0));
        verify(geograficLocationMapper, times(1)).mapFromEntity(geographicalLocationEntityList.get(1));
    }

    @Test
    void testGetAllGeGeographicalLocation_Empty() {
        // Configurar mocks para devolver una lista vacía
        when(geographicalLocationRepositoryPort.getAllGeGeographicalLocation()).thenReturn(new ArrayList<>());

        // Ejecutar el método a probar
        List<GeographicalLocationResponse> result = geographicalLocationService.getAllGeGeographicalLocation();

        // Verificar que el resultado sea una lista vacía
        assertTrue(result.isEmpty(), "El resultado debería ser una lista vacía");
        verify(geographicalLocationRepositoryPort, times(1)).getAllGeGeographicalLocation();
        verify(geograficLocationMapper, never()).mapFromEntity(any(GeographicalLocationEntity.class));
    }

    @Test
    void testGetAllGeGeographicalLocation_SingleEntity() {
        // Configurar mocks para devolver una lista con un solo GeographicalLocationEntity
        GeographicalLocationEntity singleEntity = new GeographicalLocationEntity();
        singleEntity.setId(3);
        singleEntity.setName("Location 3");

        GeographicalLocationResponse singleResponse = new GeographicalLocationResponse();
        singleResponse.setId(3);
        singleResponse.setName("Location 3");

        when(geographicalLocationRepositoryPort.getAllGeGeographicalLocation()).thenReturn(List.of(singleEntity));
        when(geograficLocationMapper.mapFromEntity(singleEntity)).thenReturn(singleResponse);

        // Ejecutar el método a probar
        List<GeographicalLocationResponse> result = geographicalLocationService.getAllGeGeographicalLocation();

        // Verificar que el resultado contenga un solo elemento
        assertEquals(1, result.size(), "El tamaño de la lista de resultados debería ser 1");
        assertEquals(singleResponse, result.get(0), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        verify(geographicalLocationRepositoryPort, times(1)).getAllGeGeographicalLocation();
        verify(geograficLocationMapper, times(1)).mapFromEntity(singleEntity);
    }
}
