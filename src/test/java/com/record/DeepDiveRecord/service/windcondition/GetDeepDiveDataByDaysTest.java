package com.record.DeepDiveRecord.service.windcondition;

import com.record.DeepDiveRecord.application.service.WindConditionsService;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetData;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;
import com.record.DeepDiveRecord.domain.model.exception.InvalidDiveDayDataException;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.WindConditionsMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl.WindConditionsMapperImpl;
import com.record.DeepDiveRecord.service.data.DataUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class GetDeepDiveDataByDaysTest {
    @InjectMocks
    private WindConditionsService windConditionsService;
    @Mock
    private WindConditionsPort windConditionsPort;
    WindConditionsMapper windConditionsMapper = new WindConditionsMapperImpl();

    @Test
    void testGetDeepDiveDataByDaysPeers_Success() {
        InGetDataWeek input = DataUtil.getDeepDiveDataByDaysOk();
        List<WindConditionsEntity> list = new ArrayList<>();
        list.add(DataUtil.getWindConditionsEntityMorningOk());
        list.add(DataUtil.getWindConditionsEntityAfternoon1Ok());
        list.add(DataUtil.getWindConditionsEntityAfternoon2Ok());
        Page<WindConditionsEntity> pageList = new PageImpl<>(list);
        when(windConditionsPort.getDeepDiveDataByDays(any(), anyBoolean())).thenReturn(pageList);


        OutGetDataList res = windConditionsService.getDeepDiveDataByDays(input);
        //Comprobaciones para la parte de outGetData
        assertEquals(3, res.getOutGetDataList().size(), "El tamaño de la lista de resultados debería ser 1");
        assertEquals(input.getSite(), res.getOutGetDataList().get(0).getSite(), "El lugar debe ser 487006");
        assertEquals(2024, res.getOutGetDataList().get(0).getYear(), "El año debe ser 2025");
        assertEquals(7, res.getOutGetDataList().get(0).getMonth(), "El mes debe ser 7");
        assertEquals(17, res.getOutGetDataList().get(0).getDay(), "El dia debe ser 17");
        assertEquals("12", res.getOutGetDataList().get(0).getTimeOfDay(), "El dia debe ser 14");
        assertEquals(14, res.getOutGetDataList().get(0).getWind(), "El viento debe ser 14");
        assertEquals(new BigDecimal("78"), res.getOutGetDataList().get(0).getWindDirection(), "La direccion del viento debe ser 78");
        assertEquals(25.0, res.getOutGetDataList().get(0).getGustsOfWind(), "Las rachas del viento debe ser 25.0");
        assertEquals(0.8, res.getOutGetDataList().get(0).getWaveHeight(), "Las olas deben tener una altura de 0.8");
        assertEquals(9, res.getOutGetDataList().get(0).getWavePeriod(), "Las olas deben tener un periodo de 9");
        assertEquals(320, res.getOutGetDataList().get(0).getWaveDirection(), "Las olas deben tener una direccion de 320");
        assertEquals(23, res.getOutGetDataList().get(0).getEarthTemperature(), "La temperatura en tierra debe de ser de 23");
        assertEquals("20", res.getOutGetDataList().get(0).getWaterTemperature(), "La temperatura en agua debe de ser de 20");
        assertEquals(120, res.getOutGetDataList().get(0).getF1(), "El tipo de tiempo debe de ser 120");
        assertEquals("muy nuboso", res.getOutGetDataList().get(0).getDescription1(), "El tipo de clipa debe de ser muy nuboso");


        //Comprobaciones para la parte de outGetDataMedia
        assertEquals( res.getOutGetDataMediaList().size()%2==0, true, "El tamaño de la lista de resultados debería ser par (en este caso 2)");
        assertEquals(1, res.getOutGetDataMediaList().get(0).getTimeOfDay(), "El time of day debe ser 1 cuando la hora es inferior a 14");
        assertEquals(2, res.getOutGetDataMediaList().get(1).getTimeOfDay(), "El time of day debe ser 2 cuando la hora es superior a 13");
        assertEquals(11, res.getOutGetDataMediaList().get(1).getMinWinter(), "La velocidad del viento minima es 11");
        assertEquals(12, res.getOutGetDataMediaList().get(1).getMaxWinter(), "La velocidad del viento maxima es 12");
        assertEquals(19, res.getOutGetDataMediaList().get(1).getMinGustsOfWind(), "La racha del viento minima es 19");
        assertEquals(20, res.getOutGetDataMediaList().get(1).getMaxGustsOfWind(), "La racha del viento maxima es 20");
        assertEquals(0.7, res.getOutGetDataMediaList().get(1).getMinWaveHeight(), "La altura de ola minima es 0.7");
        assertEquals(0.9, res.getOutGetDataMediaList().get(1).getMaxWaveHeight(), "La altura de ola maxima es 0.9");
        assertEquals(8, res.getOutGetDataMediaList().get(1).getMinWavePeriod(), "El periodo de ola minimo es 8");
        assertEquals(9, res.getOutGetDataMediaList().get(1).getMaxWavePeriod(), "El periodo de ola maximo es 9");
        assertEquals(24, res.getOutGetDataMediaList().get(1).getMinEarthTemperature(), "La temperatura terrestre minima es 24");
        assertEquals(24, res.getOutGetDataMediaList().get(1).getMaxEarthTemperature(), "La temperatura terrestre maxima es 24");
        assertEquals("21", res.getOutGetDataMediaList().get(1).getMinWaterTemperature(), "La temperatura del agua minima es 21");
        assertEquals("22", res.getOutGetDataMediaList().get(1).getMaxWaterTemperature(), "La temperatura del agua maxima es 22");

        verify(windConditionsPort, times(1)).getDeepDiveDataByDays(any(), eq(false));
    }

    @Test
    void testGetDeepDiveDataByDays_EmptyPage() {
        InGetDataWeek input = DataUtil.getDeepDiveDataByDaysOk();

        // Configurar mocks para devolver una lista vacía
        Page<WindConditionsEntity> emptyPage = new PageImpl<>(new ArrayList<>());
        when(windConditionsPort.getDeepDiveDataByDays(any(), eq(false))).thenReturn(emptyPage);

        // Ejecutar el método a probar
        OutGetDataList result = windConditionsService.getDeepDiveDataByDays(input);

        // Verificar que el resultado contenga una lista vacía
        assertEquals(0, result.getOutGetDataList().size(), "El tamaño de la lista de resultados debería ser 0");
        assertEquals(0, result.getOutGetDataMediaList().size(), "El tamaño de la lista de resultados debería ser 0");
        verify(windConditionsPort, times(1)).getDeepDiveDataByDays(any(), eq(false));
    }


    @Test
    void testGetDeepDiveDataByDays_BadInput() {
        InGetDataWeek input = new InGetDataWeek();
        // Verificar el resultado y las interacciones con page not null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setPage(-1);
        // Verificar el resultado y las interacciones con page no inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setPage(1);
        // Verificar el resultado y las interacciones con size noot null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setSize(-1);
        // Verificar el resultado y las interacciones con size inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setSize(1);
        // Verificar el resultado y las interacciones con site not null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setSite("");
        // Verificar el resultado y las interacciones con site no vacio
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setSite("456767");
        // Verificar el resultado y las interacciones con fromYear no null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromYear(-1);
        // Verificar el resultado y las interacciones con fromYear no inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromYear(1);
        // Verificar el resultado y las interacciones con fromMonth no null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromMonth(-1);
        // Verificar el resultado y las interacciones con fromMonth no inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromMonth(13);
        // Verificar el resultado y las interacciones con fromMonth no puede ser superior a 12
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromMonth(1);
        // Verificar el resultado y las interacciones con FromDay no null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromDay(-1);
        // Verificar el resultado y las interacciones con FromDay no inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromDay(-1);
        // Verificar el resultado y las interacciones con FromDay no inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromDay(32);
        // Verificar el resultado y las interacciones con FromDay no puede ser superior a 31
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setFromDay(31);
        // Verificar el resultado y las interacciones con ToYear no puede ser null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setToYear(-1);
        // Verificar el resultado y las interacciones con ToYear no puede ser inferior a menos 1
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setToYear(2025);
        // Verificar el resultado y las interacciones con ToMonth no puede ser null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setToMonth(-1);
        // Verificar el resultado y las interacciones con ToMonth no puede ser inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setToMonth(13);
        // Verificar el resultado y las interacciones con ToMonth no puede ser superior a 13
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setToMonth(12);
        // Verificar el resultado y las interacciones con ToDay no puede ser null
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setToDay(-1);
        // Verificar el resultado y las interacciones con ToDay no puede ser inferior a 0
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");

        input.setToDay(32);
        // Verificar el resultado y las interacciones con ToDay no puede ser superior a 31
        assertThatThrownBy(() -> windConditionsService.getDeepDiveDataByDays(input))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para obtener las condiciones climaticas no son válidos.");
    }
}
