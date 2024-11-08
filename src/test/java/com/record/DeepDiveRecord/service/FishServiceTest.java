package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.application.service.FishService;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class FishServiceTest {
    @Mock
    private FishPort fishRepositoryPort;

    @Mock
    private FishMapper fishMapper;

    @InjectMocks
    private FishService fishService;

    private List<FishEntity> fishEntityList;
    private List<FishResponse> fishResponseList;

    @BeforeEach
    public void setUp() {
        // Inicializar listas de prueba
        fishEntityList = new ArrayList<>();
        fishResponseList = new ArrayList<>();

        // Crear y agregar FishEntity de prueba
        FishEntity fishEntity1 = new FishEntity();
        fishEntity1.setId(1);
        fishEntity1.setName("Fish 1");
        fishEntityList.add(fishEntity1);

        FishEntity fishEntity2 = new FishEntity();
        fishEntity2.setId(2);
        fishEntity2.setName("Fish 2");
        fishEntityList.add(fishEntity2);

        // Crear y agregar FishResponse de prueba
        FishResponse fishResponse1 = new FishResponse();
        fishResponse1.setId(1);
        fishResponse1.setName("Fish 1");
        fishResponseList.add(fishResponse1);

        FishResponse fishResponse2 = new FishResponse();
        fishResponse2.setId(2);
        fishResponse2.setName("Fish 2");
        fishResponseList.add(fishResponse2);
    }

    @Test
    void testGetAllFishList_Success() {
        // Configurar mocks para devolver la lista de entidades y respuestas
        when(fishRepositoryPort.getAllFishList()).thenReturn(fishEntityList);
        when(fishMapper.fromEntity(fishEntityList.get(0))).thenReturn(fishResponseList.get(0));
        when(fishMapper.fromEntity(fishEntityList.get(1))).thenReturn(fishResponseList.get(1));

        // Ejecutar el método a probar
        List<FishResponse> result = fishService.getAllFishList();

        // Verificar el resultado y las interacciones
        assertEquals(2, result.size(), "El tamaño de la lista de resultados debería ser 2");
        assertEquals(fishResponseList, result, "La lista de respuestas debería coincidir con la lista de prueba");
        verify(fishRepositoryPort, times(1)).getAllFishList();
        verify(fishMapper, times(1)).fromEntity(fishEntityList.get(0));
        verify(fishMapper, times(1)).fromEntity(fishEntityList.get(1));
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
        verify(fishMapper, never()).fromEntity(any(FishEntity.class));
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
        when(fishMapper.fromEntity(singleFishEntity)).thenReturn(singleFishResponse);

        // Ejecutar el método a probar
        List<FishResponse> result = fishService.getAllFishList();

        // Verificar que el resultado contenga un solo elemento
        assertEquals(1, result.size(), "El tamaño de la lista de resultados debería ser 1");
        assertEquals(singleFishResponse, result.get(0), "El único elemento en la lista de resultados debería coincidir con el elemento de prueba");
        verify(fishRepositoryPort, times(1)).getAllFishList();
        verify(fishMapper, times(1)).fromEntity(singleFishEntity);
    }
}
