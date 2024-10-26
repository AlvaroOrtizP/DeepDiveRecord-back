package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.application.service.WindConditionsService;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.common.Pagination;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetData;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.CommonMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.WindConditionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class WindConditionsServiceTest {
    @Mock
    private WindConditionsPort windConditionsPort;

    @Mock
    private WindConditionsMapper windConditionsMapper;

    @Mock
    private CommonMapper commonMapper;

    @InjectMocks
    private WindConditionsService windConditionsService;

    private InGetDataWeek inGetDataWeek;
    private List<WindConditionsEntity> windConditionsEntityList;
    private Page<WindConditionsEntity> windConditionsEntityPage;
    private List<OutGetData> outGetDataList;
    private OutGetDataList outGetDataListResponse;
    private Pagination pagination;

    @BeforeEach
    void setUp() {
        // Configuración de datos de prueba
        inGetDataWeek = new InGetDataWeek();
        inGetDataWeek.setFromDay(1);
        windConditionsEntityList = new ArrayList<>();
        WindConditionsEntity entity1 = new WindConditionsEntity();
        WindConditionsEntity entity2 = new WindConditionsEntity();
        windConditionsEntityList.add(entity1);
        windConditionsEntityList.add(entity2);

        windConditionsEntityPage = new PageImpl<>(windConditionsEntityList);

        outGetDataList = new ArrayList<>();
        OutGetData outGetData1 = new OutGetData();
        OutGetData outGetData2 = new OutGetData();
        outGetDataList.add(outGetData1);
        outGetDataList.add(outGetData2);

        pagination = new Pagination();
        outGetDataListResponse = new OutGetDataList();
        outGetDataListResponse.setOutGetDataList(outGetDataList);
        outGetDataListResponse.setPagination(pagination);
    }

    @Test
    void testGetDeepDiveDataByDays_Success() {
        // Configurar mocks para devolver datos de prueba
        when(windConditionsPort.getDeepDiveDataByDays(any(), eq(false))).thenReturn(windConditionsEntityPage);
        OutGetData outGetData1 = new OutGetData();
        OutGetData outGetData2 = new OutGetData();

        when(windConditionsMapper.getOutGetData(any(WindConditionsEntity.class)))
                .thenReturn(outGetData1)
                .thenReturn(outGetData2);
        when(commonMapper.getPagination(any())).thenReturn(pagination);

        // Ejecutar el método a probar
        OutGetDataList result = windConditionsService.getDeepDiveDataByDays(inGetDataWeek);

        // Verificar el resultado y las interacciones
        assertEquals(2, result.getOutGetDataList().size(), "El tamaño de la lista de resultados debería ser 2");
        assertEquals(pagination, result.getPagination(), "La paginación en la respuesta debería coincidir con la de prueba");
        verify(windConditionsPort, times(1)).getDeepDiveDataByDays(any(), eq(false));
        verify(windConditionsMapper, times(2)).getOutGetData(any(WindConditionsEntity.class));
        verify(commonMapper, times(1)).getPagination(any());
    }

    @Test
    void testGetDeepDiveDataByDays_EmptyPage() {
        // Configurar mocks para devolver una lista vacía
        Page<WindConditionsEntity> emptyPage = new PageImpl<>(new ArrayList<>());
        when(windConditionsPort.getDeepDiveDataByDays(any(), eq(false))).thenReturn(emptyPage);
        when(commonMapper.getPagination(any())).thenReturn(pagination);

        // Ejecutar el método a probar
        OutGetDataList result = windConditionsService.getDeepDiveDataByDays(inGetDataWeek);

        // Verificar que el resultado contenga una lista vacía
        assertEquals(0, result.getOutGetDataList().size(), "El tamaño de la lista de resultados debería ser 0");
        assertEquals(pagination, result.getPagination(), "La paginación en la respuesta debería coincidir con la de prueba");
        verify(windConditionsPort, times(1)).getDeepDiveDataByDays(any(), eq(false));
        verify(windConditionsMapper, never()).getOutGetData(any(WindConditionsEntity.class));
        verify(commonMapper, times(1)).getPagination(any());
    }

    @Test
    void testGetDeepDiveDataByDays_SingleEntity() {
        // Configurar mocks para devolver una lista con un solo WindConditionsEntity
        WindConditionsEntity singleEntity = new WindConditionsEntity();
        Page<WindConditionsEntity> singleEntityPage = new PageImpl<>(List.of(singleEntity));
        OutGetData singleOutGetData = new OutGetData();

        // Usa el matcher any() en lugar del valor real
        when(windConditionsPort.getDeepDiveDataByDays(any(), eq(false))).thenReturn(singleEntityPage);
        when(windConditionsMapper.getOutGetData(any(WindConditionsEntity.class))).thenReturn(singleOutGetData);
        when(commonMapper.getPagination(any())).thenReturn(pagination);

        // Ejecutar el método a probar
        OutGetDataList result = windConditionsService.getDeepDiveDataByDays(inGetDataWeek);

        // Verificar que el resultado contenga un solo elemento
        assertEquals(1, result.getOutGetDataList().size(), "El tamaño de la lista de resultados debería ser 1");
        assertEquals(singleOutGetData, result.getOutGetDataList().get(0), "El único elemento en la lista de resultados debería coincidir con el de prueba");
        assertEquals(pagination, result.getPagination(), "La paginación en la respuesta debería coincidir con la de prueba");
        verify(windConditionsPort, times(1)).getDeepDiveDataByDays(any(), eq(false));
        verify(windConditionsMapper, times(1)).getOutGetData(any(WindConditionsEntity.class));
        verify(commonMapper, times(1)).getPagination(any());
    }
}
