package com.record.DeepDiveRecord.service.diveday;

import com.record.DeepDiveRecord.application.service.DiveDayService;
import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.model.exception.InvalidDiveDayDataException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.service.data.DataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * En esta clase comprobaremos todas las casuisticas posibles en la creacion de un DiveDay (Dia de buceo)
 */
@DataJpaTest
@ActiveProfiles("test")
class CreateDiveDayTest {
    @Mock
    private DiveDayPort diveDayPort;

    @Mock
    private GeographicalLocationPort geographicalLocationPort;

    @Mock
    private DiveDayMapper diveDayMapper;

    @InjectMocks
    private DiveDayService diveDayService;

    @BeforeEach
    void setUp() {
    }

    /**
     * Test para comprobar el flujo correcto.
     *
     * 1 - Tendran que venir todos los datos en el objeto InCreateDailyDiving
     * 2 - Encontrar una localizacion geografica existente con los datos proporcionados
     * 3 - Guardar el nuevo registros
     *
     */
    @Test
    @Transactional
    void testCreateDiveDay_Success() {
        // Configurar mocks para devolver datos simulados
        GeographicalLocationEntity geographicalLocationEntity = new GeographicalLocationEntity();
        geographicalLocationEntity.setIdWindwuru("487006");
        geographicalLocationEntity.setSite("Isla");
        geographicalLocationEntity.setName("Acantilado-Oeste");
        when(geographicalLocationPort.findById(487006)).thenReturn(geographicalLocationEntity);

        DiveDayEntity diveDayEntity = new DiveDayEntity();
        diveDayEntity.setDiveDayId(1);
        when(diveDayMapper.entityFromResponse(any(InCreateDailyDiving.class))).thenReturn(diveDayEntity);
        when(diveDayPort.save(any(DiveDayEntity.class))).thenReturn(diveDayEntity);

        // Ejecutar el método a probar
        InCreateDailyDiving inCreateDailyDiving = DataUtil.getCreateDailyDivingOk();
        Integer result = diveDayService.createDiveDay(inCreateDailyDiving);

        // Verificar el resultado y las interacciones
        assertEquals(1, result, "El ID del DiveDay debería coincidir");
        verify(geographicalLocationPort, times(1)).findById(anyInt());
        verify(diveDayMapper, times(1)).entityFromResponse(any(InCreateDailyDiving.class));
        verify(diveDayPort, times(1)).save(any(DiveDayEntity.class));
    }

    /**
     * Test para comprobar cada una de los posibles valores erroneos en la entrada
     */
    @Test
    @Transactional
    void testCreateDiveDay_Fail_InputError() {

        InCreateDailyDiving invalidInput = new InCreateDailyDiving();

        // ---- DAY

        // Verificar el resultado y las interacciones con day null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setDay("370");
        // Verificar el resultado y las interacciones con day fuera del rango
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");


        invalidInput.setDay("0");
        // Verificar el resultado y las interacciones con day fuera del rango
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setDay("5");

        // ---- MONTH


        // Verificar el resultado y las interacciones con month null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setMonth("13");
        // Verificar el resultado y las interacciones con month null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setMonth("0");
        // Verificar el resultado y las interacciones con month null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setMonth("2");

        // ---- YEAR
        // Verificar el resultado y las interacciones con year null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");


        invalidInput.setYear("202");
        // Verificar el resultado y las interacciones con year no igual a 4
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setYear("20222");
        // Verificar el resultado y las interacciones con year no igual a 4
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setYear("2022");



        // ---- BEGINNING

        // Verificar el resultado y las interacciones con Beginning null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setBeginning("10-22");
        // Verificar el resultado y las interacciones con Beginning sin :
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setBeginning("10:22");
        // Verificar el resultado y las interacciones con Beginning null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setBeginning("A0:22");
        // Verificar el resultado y las interacciones con Beginning para que no vengan letras
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setEnd("10_22");
        // Verificar el resultado y las interacciones con Beginning para que no vengan letras
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setEnd("101:22");
        // Verificar el resultado y las interacciones con Beginning para que no vengan letras
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        // ---- END
        // Verificar el resultado y las interacciones con end sin :
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setEnd("10:22");
        // Verificar el resultado y las interacciones con assessment null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");


        invalidInput.setEnd("A0:22");
        // Verificar el resultado y las interacciones con end para que no vengan letras
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setEnd("123:22");
        // Verificar el resultado y las interacciones con end para que no vengan letras
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setEnd("10:22");

        // ---- ASSESSEMENT
        // Verificar el resultado y las interacciones con assessment null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setAssessment(0);
        // Verificar el resultado y las interacciones con assessment inferior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setAssessment(6);
        // Verificar el resultado y las interacciones con assessment superior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setAssessment(3);


        // ---- JELLYFISH

        // Verificar el resultado y las interacciones con jellyfish null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setJellyfish(0);
        // Verificar el resultado y las interacciones con jellyfish inferior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setJellyfish(4);
        // Verificar el resultado y las interacciones con jellyfish supeior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setJellyfish(2);

        // ---- VISIBILITY

        // Verificar el resultado y las interacciones con visibility null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setVisibility(0);
        // Verificar el resultado y las interacciones con visibility inferior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setVisibility(5);
        // Verificar el resultado y las interacciones con visibility superior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setVisibility(3);

        // ---- SEA BACKGROUND

        // Verificar el resultado y las interacciones con seaBackground null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setSeaBackground(0);
        // Verificar el resultado y las interacciones con seaBacground inferior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setSeaBackground(5);
        // Verificar el resultado y las interacciones con seaBacground superior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setSeaBackground(4);

        // ---- FISH GRASS

        // Verificar el resultado y las interacciones con fishGrass null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setFishGrass(0);
        // Verificar el resultado y las interacciones con fishGrass inferior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setFishGrass(4);
        // Verificar el resultado y las interacciones con fishGrass supeior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setFishGrass(3);

        // ---- PRESENCE PLASTIC

        // Verificar el resultado y las interacciones con presencePlastic null
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setFishGrass(0);
        // Verificar el resultado y las interacciones con presencePlastic inferior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

        invalidInput.setFishGrass(4);
        // Verificar el resultado y las interacciones con presencePlastic supeior al valor permitido
        assertThatThrownBy(() -> diveDayService.createDiveDay(invalidInput))
                .isInstanceOf(InvalidDiveDayDataException.class)
                .hasMessageContaining("Los datos para crear el DiveDay no son válidos.");

    }

    /**
     * Caso no se encuentra la localizacion
     */
    @Test
    @Transactional
    void testCreateDiveDay_Fail_GeographicalLocation() {
        // Configurar mocks para devolver datos simulados
        when(geographicalLocationPort.findById(487005)).thenThrow(new EntityNotFoundException("Geographical location not found"));

        // Ejecutar el método a probar
        InCreateDailyDiving inCreateDailyDiving = DataUtil.getCreateDailyDivingOk();
        inCreateDailyDiving.setIdGeographicLocation(487005);
        assertThrows(EntityNotFoundException.class, () -> diveDayService.createDiveDay(inCreateDailyDiving));

        // Verificar el resultado y las interacciones
        verify(geographicalLocationPort, times(1)).findById(anyInt());
        verify(diveDayPort, times(0)).save(any(DiveDayEntity.class));
    }



    /**
     * Error al crear (guardar) la instancia
     */
    @Test
    @Transactional
    void testCreateDiveDay_Fail_Save() {
        // Configurar mocks para devolver datos simulados
        GeographicalLocationEntity geographicalLocationEntity = new GeographicalLocationEntity();
        geographicalLocationEntity.setIdWindwuru("487006");
        geographicalLocationEntity.setSite("Isla");
        geographicalLocationEntity.setName("Acantilado-Oeste");
        when(geographicalLocationPort.findById(487006)).thenReturn(geographicalLocationEntity);

        DiveDayEntity diveDayEntity = new DiveDayEntity();
        diveDayEntity.setDiveDayId(1);
        when(diveDayMapper.entityFromResponse(any(InCreateDailyDiving.class))).thenReturn(diveDayEntity);
        when(diveDayPort.save(any(DiveDayEntity.class))).thenReturn(diveDayEntity);
        when(diveDayPort.save(any(DiveDayEntity.class))).thenThrow(new RuntimeException());


        // Ejecutar el método a probar
        InCreateDailyDiving inCreateDailyDiving = DataUtil.getCreateDailyDivingOk();


        // Verificar el resultado y las interacciones
        assertThrows(RuntimeException.class, () -> diveDayService.createDiveDay(inCreateDailyDiving));
        verify(geographicalLocationPort, times(1)).findById(anyInt());
        verify(diveDayMapper, times(1)).entityFromResponse(any(InCreateDailyDiving.class));
        verify(diveDayPort, times(1)).save(any(DiveDayEntity.class));
    }
}
