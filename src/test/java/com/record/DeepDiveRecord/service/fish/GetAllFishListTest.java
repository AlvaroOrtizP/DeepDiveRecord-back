package com.record.DeepDiveRecord.service.fish;

import com.record.DeepDiveRecord.application.service.FishService;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
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
class GetAllFishListTest {
    @Mock
    private FishPort fishRepositoryPort;
    @InjectMocks
    private FishService fishService;

    @Test
    void testGetAllFishList_Success() {

        // Configurar mocks para devolver la lista de entidades y respuestas
        List<FishEntity> fishEntityList = new ArrayList<>();
        fishEntityList.add(DataUtil.getFishEntityOk(1));
        fishEntityList.add(DataUtil.getFishEntityOk(2));

        when(fishRepositoryPort.getAllFishList()).thenReturn(fishEntityList);

        // Ejecutar el método a probar
        List<FishResponse> result = fishService.getAllFishList();

        // Verificar el resultado y las interacciones
        assertEquals(2, result.size(), "El tamaño de la lista de resultados debería ser 2");
        assertEquals(fishEntityList.get(1).getName(), result.get(1).getName(), "Debe contener lo mismo");
        verify(fishRepositoryPort, times(1)).getAllFishList();

    }

    @Test
    void testGetAllFishList_Empty() {
        // Configurar mocks para devolver una lista vacía
        when(fishRepositoryPort.getAllFishList()).thenReturn(new ArrayList<>());

        // Ejecutar el método a probar
        List<FishResponse> result = fishService.getAllFishList();

        // Verificar que el resultado sea una lista vacía
        assertTrue(result.isEmpty(), "El resultado debería ser una lista vacía");
        verify(fishRepositoryPort, times(1)).getAllFishList();
    }

    @Test
    void testGetAllFishList_SingleEntity() {
        // Configurar mocks para devolver una lista con un solo FishEntity
        FishEntity singleFishEntity = new FishEntity();
        singleFishEntity.setId(3);
        singleFishEntity.setName("Fish 3");

        FishResponse singleFishResponse = new FishResponse();
        singleFishResponse.setId(3);
        singleFishResponse.setName("Fish 3");

        when(fishRepositoryPort.getAllFishList()).thenReturn(List.of(singleFishEntity));

        // Ejecutar el método a probar
        List<FishResponse> result = fishService.getAllFishList();

        // Verificar que el resultado contenga un solo elemento
        assertEquals(1, result.size(), "El tamaño de la lista de resultados debería ser 1");
        assertEquals(singleFishResponse.getId(), result.get(0).getId(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleFishResponse.getName(), result.get(0).getName(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleFishResponse.getSite(), result.get(0).getSite(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleFishResponse.getFirstSighting(), result.get(0).getFirstSighting(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleFishResponse.getFirstLast(), result.get(0).getFirstLast(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleFishResponse.getStartSeason(), result.get(0).getStartSeason(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleFishResponse.getEndSeason(), result.get(0).getEndSeason(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        assertEquals(singleFishResponse.getFirstLifeWarning(), result.get(0).getFirstLifeWarning(), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");

        verify(fishRepositoryPort, times(1)).getAllFishList();
    }
}
