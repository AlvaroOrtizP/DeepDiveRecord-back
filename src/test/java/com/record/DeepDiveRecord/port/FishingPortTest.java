package com.record.DeepDiveRecord.port;

import com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl.FishingRepositoryImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.fishing.FishingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class FishingPortTest {
    @Mock
    private FishingRepository fishingRepository;

    @InjectMocks
    private FishingRepositoryImpl fishingRepositoryImpl;

    private FishingEntity fishingEntity;

    @BeforeEach
    void setUp() {
        // Configura un ejemplo de FishingEntity
        fishingEntity = new FishingEntity();
        fishingEntity.setId(1);
        FishEntity fish = new FishEntity();
        fish.setId(1);
        fishingEntity.setFish(fish);

    }

    @Test
    @Transactional
    void testSave() {
        // Simula el comportamiento del repositorio
        when(fishingRepository.save(fishingEntity)).thenReturn(fishingEntity);

        // Llama al método y verifica el resultado
        Integer result = fishingRepositoryImpl.save(fishingEntity);
        assertEquals(fishingEntity.getId(), result);

        // Verifica que se haya llamado al método save una vez
        verify(fishingRepository, times(1)).save(fishingEntity);
    }
}
