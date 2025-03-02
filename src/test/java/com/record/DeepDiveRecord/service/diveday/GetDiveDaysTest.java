package com.record.DeepDiveRecord.service.diveday;

import com.record.DeepDiveRecord.application.service.DiveDayService;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class GetDiveDaysTest {
    @Mock
    private DiveDayPort diveDayPort;
    @InjectMocks
    private DiveDayService diveDayService;
    @BeforeEach
    void setUp() {

    }
    @Test
    void testFindByFilters_Success() {

        when(diveDayPort.findByFilters("Isla", PageRequest.of(0, 10))).thenReturn(DataUtils.getDiveDayPage());

        // Ejecutar el método a probar
        Page<DiveDayResponse> response = diveDayService.findByFilters("Isla", PageRequest.of(0, 10));

        // Verificar las interacciones y el resultado
        verify(diveDayPort, times(1)).findByFilters("Isla", PageRequest.of(0, 10));

        assertEquals(1, response.getTotalElements(), "Debería haber 1 condición de viento en la respuesta");
        assertEquals("Acantilado-Isla-Este", response.getContent().get(0).getSite());
    }
    @Test
    void testFindByFilters_Empty() {
        List<DiveDayEntity> list = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.fromString("asc"), "fecha");
        when(diveDayPort.findByFilters("Isla2", PageRequest.of(0, 10))).thenReturn(new PageImpl<>(list, pageable, 1));

        // Ejecutar el método a probar
        Page<DiveDayResponse> response = diveDayService.findByFilters("Isla", PageRequest.of(0, 10));

        // Verificar las interacciones y el resultado
        verify(diveDayPort, times(1)).findByFilters("Isla", PageRequest.of(0, 10));

        assertEquals(0, response.getTotalElements(), "Debería haber 0 elementos en la respuesta");
    }
}
