package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.application.service.FishingService;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.FishingDetails;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishingMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
    @Mock
    private GeograficLocationMapper geograficLocationMapper;

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

    @Test
    void testGetFishingById_Success() {


        when(fishingPort.getById(any(Integer.class))).thenReturn(getAFishingEntity());
        when(fishingMapper.fromEntityToResponse(any(FishingEntity.class))).thenReturn(getAFishingDetails());

        FishingDetails res = fishingService.getFishingById(1);
        assertEquals(res.getFishId(), 1);

    }
    private FishingEntity getAFishingEntity(){
        FishingEntity entity = new FishingEntity();
        entity.setId(1);
        entity.setNotes("prueba");
        entity.setCaught(true);
        entity.setWeight(new BigDecimal("12.2"));
        FishEntity fish = new FishEntity();
        fish.setId(1);
        fish.setName("Pez Ballesta");
        fish.setSite("España");
        fish.setFirstSighting("0105");
        fish.setFirstLast("1212");
        fish.setStartSeason("1109");
        fish.setEndSeason("1412");
        fish.setFirstLifeWarning("12122024");
        entity.setFish(fish);
        entity.setLongG(2);
        entity.setLatG(2);
        GeographicalLocationEntity geographicalLo = new GeographicalLocationEntity();
        geographicalLo.setId(1);
        geographicalLo.setName("Isla");
        geographicalLo.setSite("Oeste");
        entity.setGeographicalLocation(geographicalLo);
        return entity;
    }
    private FishingDetails getAFishingDetails(){
        FishingDetails details = new FishingDetails();
        details.setId(1);
        details.setNotes("prueba");
        details.setCaught(true);
        details.setWeight(new BigDecimal("12.2"));
        details.setFishId(1);
        details.setName("Pez Ballesta");
        details.setSite("España");
        details.setFirstSighting("0105");
        details.setFirstLast("1212");
        details.setStartSeason("1109");
        details.setEndSeason("1412");
        details.setFirstLifeWarning("12122024");
        details.setLogG(2);
        details.setLatG(2);
        details.setGeographieId(1);
        details.setGeographieName("Isla");
        details.setGeographieSite("Oeste");
        return details;
    }
}