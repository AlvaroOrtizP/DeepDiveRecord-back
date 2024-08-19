package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.application.service.DiveDayService;
import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.*;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.domain.port.TideTablePort;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.*;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class DiveDayServiceTest {

    @Mock
    private DiveDayPort diveDayPort;

    @Mock
    private TideTablePort tideTablePort;

    @Mock
    private GeographicalLocationPort geographicalLocationPort;

    @Mock
    private WindConditionsPort windConditionsPort;

    @Mock
    private DiveDayMapper diveDayMapper;

    @Mock
    private GeograficLocationMapper geograficLocationMapper;

    @Mock
    private TideTableMapper tideTableMapper;

    @Mock
    private WindConditionsMapper windConditionsMapper;

    @Mock
    private FishMapper fishMapper;

    @InjectMocks
    private DiveDayService diveDayService;

    private InCreateDailyDiving inCreateDailyDiving;
    private DiveDayEntity diveDayEntity;
    private GeographicalLocationEntity geographicalLocationEntity;
    private TideTableEntity tideTableEntity;
    private WindConditionsEntity windConditionsEntity;

    @BeforeEach
    void setUp() {
        // Inicialización de objetos simulados para las pruebas
        inCreateDailyDiving = new InCreateDailyDiving();
        inCreateDailyDiving.setIdGeograficLocation(1);

        geographicalLocationEntity = new GeographicalLocationEntity();
        geographicalLocationEntity.setId(1);
        geographicalLocationEntity.setIdWindwuru("SomeSite");

        diveDayEntity = new DiveDayEntity();
        diveDayEntity.setDiveDayId(1);
        diveDayEntity.setGeographicalLocation(geographicalLocationEntity);

        tideTableEntity = new TideTableEntity();
        windConditionsEntity = new WindConditionsEntity();
    }

    @Test
    @Transactional
    void testCreateDiveDay_Success() {
        // Configurar mocks para devolver datos simulados
        when(geographicalLocationPort.findById(anyInt())).thenReturn(geographicalLocationEntity);
        when(diveDayMapper.entityFromResponse(any(InCreateDailyDiving.class))).thenReturn(diveDayEntity);
        when(diveDayPort.save(any(DiveDayEntity.class))).thenReturn(diveDayEntity);

        // Ejecutar el método a probar
        Integer result = diveDayService.createDiveDay(inCreateDailyDiving);

        // Verificar el resultado y las interacciones
        assertEquals(1, result, "El ID del DiveDay debería coincidir");
        verify(geographicalLocationPort, times(1)).findById(anyInt());
        verify(diveDayMapper, times(1)).entityFromResponse(any(InCreateDailyDiving.class));
        verify(diveDayPort, times(1)).save(any(DiveDayEntity.class));
    }

    @Test
    void testFindDiveDayById_Success() {
        // Configurar mocks para devolver datos simulados
        List<FishingEntity> fishingList = new ArrayList<>();
        FishingEntity fishing = new FishingEntity();
        fishingList.add(fishing);
        diveDayEntity.setFishingEntities(fishingList);
        when(diveDayPort.findById(anyInt())).thenReturn(diveDayEntity);
        when(diveDayMapper.responseFromEntity(any(DiveDayEntity.class))).thenReturn(new DiveDayDetailsResponse());
        when(geograficLocationMapper.responseFromDiveDayEntity(any(DiveDayEntity.class))).thenReturn(new GeographicalLocationResponse());

        when(tideTablePort.findById(any())).thenReturn(Optional.of(tideTableEntity));
        when(tideTableMapper.responseFromEntity(any(TideTableEntity.class))).thenReturn(new TideTableResponse());

        Page<WindConditionsEntity> windConditionsPage = new PageImpl<>(Collections.singletonList(windConditionsEntity));
        when(windConditionsPort.getDeepDiveDataByDays(any(),eq(false))).thenReturn(windConditionsPage);
        when(windConditionsMapper.responseFromEntity(any(WindConditionsEntity.class))).thenReturn(new WindConditionResponse());

        when(fishMapper.responseFromEntity(any(FishingEntity.class))).thenReturn(new FishingResponse());

        // Ejecutar el método a probar
        DiveDayDetailsResponse response = diveDayService.findDiveDayById(1);

        // Verificar las interacciones y el resultado
        verify(diveDayPort, times(1)).findById(anyInt());
        verify(diveDayMapper, times(1)).responseFromEntity(any(DiveDayEntity.class));
        verify(geograficLocationMapper, times(1)).responseFromDiveDayEntity(any(DiveDayEntity.class));
        verify(tideTablePort, times(1)).findById(any());
        verify(tideTableMapper, times(1)).responseFromEntity(any(TideTableEntity.class));
        verify(windConditionsPort, times(1)).getDeepDiveDataByDays(any(), eq(false));
        verify(windConditionsMapper, times(1)).responseFromEntity(any(WindConditionsEntity.class));
        verify(fishMapper, times(1)).responseFromEntity(any(FishingEntity.class));

        assertEquals(1, response.getWindConditionsList().size(), "Debería haber 1 condición de viento en la respuesta");
    }

    @Test
    void testFindDiveDayById_EntityNotFound() {
        // Configurar mocks para lanzar excepción
        when(diveDayPort.findById(anyInt())).thenThrow(new EntityNotFoundException("DiveDay not found"));

        // Verificar que se lance la excepción
        assertThrows(EntityNotFoundException.class, () -> diveDayService.findDiveDayById(1));

        // Verificar que los métodos apropiados fueron llamados
        verify(diveDayPort, times(1)).findById(anyInt());
    }
}