package com.record.DeepDiveRecord.port;

import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Test
    void testFind_Success() {
        when(fishingRepository.findById(1)).thenReturn(Optional.of(fishingEntity));
        FishingEntity result = fishingRepositoryImpl.getById(1);
        assertEquals(fishingEntity.getId(), result.getId());

    }
    @Test
    void testFind_Failed() {
        // Configurar el mock para devolver un Optional vacío
        when(fishingRepository.findById(1)).thenReturn(Optional.ofNullable(null));

        // Verificar que se lanza la excepción EntityNotFoundException al llamar a getById
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            fishingRepositoryImpl.getById(1);
        });

        // Verificar que el mensaje de la excepción es el esperado
        assertEquals("Fishing not found", exception.getMessage());
    }
}
