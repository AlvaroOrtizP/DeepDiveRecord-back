package com.record.DeepDiveRecord.port;

import com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl.FishRepositoryImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.fish.FishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class FishPortTest {
    @Mock
    private FishRepository fishRepository;

    @InjectMocks
    private FishRepositoryImpl fishRepositoryImpl;

    private List<FishEntity> mockFishEntityList;

    @BeforeEach
    void setUp() {
        mockFishEntityList = new ArrayList<>();

        // Configura un ejemplo de FishEntity
        FishEntity fishEntity = new FishEntity();
        fishEntity.setId(1);
        fishEntity.setName("Tuna");

        // Añade el FishEntity a la lista de mock
        mockFishEntityList.add(fishEntity);
    }

    @Test
    void testGetAllFishList() {
        // Simula el comportamiento del repositorio
        when(fishRepository.findAll()).thenReturn(mockFishEntityList);

        // Llama al método y verifica el resultado
        List<FishEntity> result = fishRepositoryImpl.getAllFishList();
        assertNotNull(result);
        assertEquals(mockFishEntityList.size(), result.size());
        assertEquals(mockFishEntityList.get(0).getName(), result.get(0).getName());

        // Verifica que se haya llamado al método findAll una vez
        verify(fishRepository, times(1)).findAll();
    }
}
