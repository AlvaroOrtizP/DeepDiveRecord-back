package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.application.service.FishingService;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class FishingServiceTest {

    @Mock
    private GeographicalLocationPort geographicalLocationPort;

    @Mock
    private FishingPort fishingPort;

    @Mock
    private FishingMapper fishingMapper;

    @InjectMocks
    private FishingService fishingService;

    private InCreateFishing inCreateFishing;
    private GeographicalLocationEntity geographicalLocationEntity;
    private FishingEntity fishingEntity;

    @BeforeEach
    public void setUp() {
        // Inicializar objetos de prueba
        inCreateFishing = new InCreateFishing();
        inCreateFishing.setName("Fishing Spot");
        inCreateFishing.setSite("Test Site");

        geographicalLocationEntity = new GeographicalLocationEntity();
        geographicalLocationEntity.setId(1);

        fishingEntity = new FishingEntity();
        fishingEntity.setId(1);
    }

    @Test
    @Transactional
    void testCreateFishing_Success() {
        // Configurar mocks para devolver datos simulados
        when(geographicalLocationPort.findByNameAndSite(any())).thenReturn(geographicalLocationEntity);
        when(fishingMapper.fromRequestToEntity(any(InCreateFishing.class))).thenReturn(fishingEntity);
        when(fishingPort.save(any(FishingEntity.class))).thenReturn(1);

        // Ejecutar el método a probar
        Integer result = fishingService.createFishing(inCreateFishing);

        // Verificar el resultado y las interacciones
        assertEquals(1, result, "El ID de la pesca debería coincidir");
        verify(geographicalLocationPort, times(1)).findByNameAndSite(any());
        verify(fishingMapper, times(1)).fromRequestToEntity(any(InCreateFishing.class));
        verify(fishingPort, times(1)).save(any(FishingEntity.class));
    }

    @Test
    @Transactional
    void testCreateFishing_GeographicalLocationNotFound() {
        // Configurar mocks para devolver null para la geolocalización
        when(geographicalLocationPort.findByNameAndSite(any())).thenReturn(null);
        when(fishingMapper.fromRequestToEntity(any(InCreateFishing.class))).thenReturn(fishingEntity);
        when(fishingPort.save(any(FishingEntity.class))).thenReturn(1);

        // Ejecutar el método a probar
        Integer result = fishingService.createFishing(inCreateFishing);

        // Verificar el resultado y las interacciones
        assertEquals(1, result, "El ID de la pesca debería coincidir");
        verify(geographicalLocationPort, times(1)).findByNameAndSite(any());
        verify(fishingMapper, times(1)).fromRequestToEntity(any(InCreateFishing.class));
        verify(fishingPort, times(1)).save(any(FishingEntity.class));
    }

    @Test
    @Transactional
    void testCreateFishing_FishingSaveFailure() {
        // Configurar mocks para devolver un valor nulo al guardar la pesca
        when(geographicalLocationPort.findByNameAndSite(any())).thenReturn(geographicalLocationEntity);
        when(fishingMapper.fromRequestToEntity(any(InCreateFishing.class))).thenReturn(fishingEntity);
        when(fishingPort.save(any(FishingEntity.class))).thenReturn(null);

        // Ejecutar el método a probar
        Integer result = fishingService.createFishing(inCreateFishing);

        // Verificar que el resultado sea nulo o se maneje según lo esperado
        assertNull(result);
        verify(geographicalLocationPort, times(1)).findByNameAndSite(any());
        verify(fishingMapper, times(1)).fromRequestToEntity(any(InCreateFishing.class));
        verify(fishingPort, times(1)).save(any(FishingEntity.class));
    }
}
