package com.record.DeepDiveRecord.service.diveday;

import com.record.DeepDiveRecord.application.service.DiveDayService;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.TideTablePort;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.service.data.DataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class GetDiveDayByIdTest {
    @Mock
    private DiveDayPort diveDayPort;
    @Mock
    private TideTablePort tideTablePort;
    @Mock
    private WindConditionsPort windConditionsPort;
    @InjectMocks
    private DiveDayService diveDayService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testFindDiveDayById_Success() {

        when(diveDayPort.findById(anyInt())).thenReturn(DataUtil.getDiveDayEntityOk());
        when(tideTablePort.findById(any())).thenReturn(Optional.of(DataUtil.getTideTableEntityOk()));

        Page<WindConditionsEntity> windConditionsPage = new PageImpl<>(Collections.singletonList(DataUtil.getWindConditionsEntityOk()));
        when(windConditionsPort.getDeepDiveDataByDays(any(), eq(false))).thenReturn(windConditionsPage);

        // Ejecutar el método a probar
        DiveDayDetailsResponse response = diveDayService.findDiveDayById(1);

        // Verificar las interacciones y el resultado
        verify(diveDayPort, times(1)).findById(anyInt());
        verify(tideTablePort, times(1)).findById(any());
        verify(windConditionsPort, times(1)).getDeepDiveDataByDays(any(), eq(false));

        assertEquals(1, response.getWindConditionsList().size(), "Debería haber 1 condición de viento en la respuesta");
        assertEquals(4, response.getFishingList().size(), "Deberia haber 4 elementos en la lista");

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
