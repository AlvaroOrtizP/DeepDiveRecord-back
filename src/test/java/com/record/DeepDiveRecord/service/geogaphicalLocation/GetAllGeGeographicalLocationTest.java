package com.record.DeepDiveRecord.service.geogaphicalLocation;

import com.record.DeepDiveRecord.application.service.GeographicalLocationService;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.service.data.DataUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class GetAllGeGeographicalLocationTest {
    @Mock
    private GeographicalLocationPort geographicalLocationRepositoryPort;
    @InjectMocks
    private GeographicalLocationService geographicalLocationService;
    @Test
    void testGetAllGeGeographicalLocation_Success() {
        // Configurar mocks para devolver la lista de entidades y respuestas
        List<GeographicalLocationEntity> geographicalLocationEntityList = new ArrayList<>();
        GeographicalLocationEntity item1 = DataUtil.getGeographicalLocationEntityOk();
        item1.setId(1);
        geographicalLocationEntityList.add(item1);
        GeographicalLocationEntity item2 = DataUtil.getGeographicalLocationEntityOk();
        item2.setId(1);
        geographicalLocationEntityList.add(item2);
        when(geographicalLocationRepositoryPort.getAllGeGeographicalLocation()).thenReturn(geographicalLocationEntityList);

        // Ejecutar el método a probar
        List<GeographicalLocationResponse> result = geographicalLocationService.getAllGeGeographicalLocation();

        // Verificar el resultado y las interacciones
        assertEquals(2, result.size(), "El tamaño de la lista de resultados debería ser 2");
        assertEquals(geographicalLocationEntityList.get(1).getId(), result.get(1).getId(), "La lista de respuestas debería coincidir con la lista de prueba");
        assertEquals(geographicalLocationEntityList.get(1).getName(), result.get(1).getName(), "La lista de respuestas debería coincidir con la lista de prueba");

        verify(geographicalLocationRepositoryPort, times(1)).getAllGeGeographicalLocation();
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

        // Ejecutar el método a probar
        List<GeographicalLocationResponse> result = geographicalLocationService.getAllGeGeographicalLocation();

        // Verificar que el resultado contenga un solo elemento
        assertEquals(1, result.size(), "El tamaño de la lista de resultados debería ser 1");
        assertEquals(singleResponse.getId(), result.get(0).getId(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleResponse.getName(), result.get(0).getName(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        verify(geographicalLocationRepositoryPort, times(1)).getAllGeGeographicalLocation();
    }
}
