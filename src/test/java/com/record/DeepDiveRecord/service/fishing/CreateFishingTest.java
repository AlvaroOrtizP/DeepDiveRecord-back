package com.record.DeepDiveRecord.service.fishing;

import com.record.DeepDiveRecord.application.service.FishingService;
import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.model.exception.InvalidFishingDataException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class CreateFishingTest {
    @Mock
    private GeographicalLocationPort geographicalLocationRepositoryPort;
    @Mock
    private FishingPort fishingPort;
    @Mock
    private DiveDayPort diveDayPort;

    @InjectMocks
    private FishingService fishingService;

    @BeforeEach
    void setUp() {
    }

    /**
     * Test de funcionamiento correcto
     */
    @Test
    @Transactional
    void testCreateFishing_Success() {
        InCreateFishing input = DataUtils.getInCreateFishing();
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        GeographicalLocationEntity geographicalLocationEntity = DataUtils.getGeographicalLocationSuccess();
        when(geographicalLocationRepositoryPort.findByNameAndSite(geographicalLocation)).thenReturn(geographicalLocationEntity);
        when(diveDayPort.findById(any())).thenReturn(new DiveDayEntity());
        when(fishingPort.save(any())).thenReturn(2);

        Integer res = fishingService.createFishing(input);
        assertEquals(2, res, "El ID del Fishing debería coincidir");
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(diveDayPort, times(1)).findById(anyInt());
        verify(fishingPort, times(1)).save(any(FishingEntity.class));

    }

    /**
     * Test para comprobar los filtros de los datos
     */
    @Test
    @Transactional
    void testCreateFishing_InputFailed() {

        InCreateFishing invalidInput = new InCreateFishing();

        // Verificar el resultado y las interacciones con fish null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setFishId(0);
        // Verificar el resultado y las interacciones con fish menor a 1
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setFishId(1);
        // Verificar el resultado y las interacciones con name null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setName("");
        // Verificar el resultado y las interacciones con name vacio
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setName("Acantilado-Oeste");
        // Verificar el resultado y las interacciones con site null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setSite("");
        // Verificar el resultado y las interacciones con site vacio
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setSite("Isla");
        // Verificar el resultado y las interacciones con notes null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setNotes("");
        // Verificar el resultado y las interacciones con notes vacio
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setNotes("prueba");
        // Verificar el resultado y las interacciones con Weight null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setWeight(new BigDecimal("1.25"));
        // Verificar el resultado y las interacciones con Lat null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setLatG(2.45);
        // Verificar el resultado y las interacciones con Long null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setLongG(2.45);
        // Verificar el resultado y las interacciones con IdDiveDay null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setIdDiveDay(0);
        // Verificar el resultado y las interacciones con IdDiveDay inferior a 0
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setIdDiveDay(1);
        // Verificar el resultado y las interacciones con SightingTime null
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setSightingTime("");
        // Verificar el resultado y las interacciones con SightingTime invalido
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setSightingTime("12-35");
        // Verificar el resultado y las interacciones con SightingTime invalido
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setSightingTime("12:135");
        // Verificar el resultado y las interacciones con SightingTime invalido
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setSightingTime("25:65");
        // Verificar el resultado y las interacciones con SightingTime invalido
        assertThatThrownBy(() -> fishingService.createFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");
    }

    /**
     * Test para comprobar el caso de no encontrar Geographical asociado (no se deberia dar)
     */
    @Test
    @Transactional
    void testCreateFishing_GeografiNotFound() {
        InCreateFishing input = DataUtils.getInCreateFishing();
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        when(geographicalLocationRepositoryPort.findByNameAndSite(any())).thenThrow(new EntityNotFoundException("Geographical location not found"));

        assertThrows(EntityNotFoundException.class, () -> fishingService.createFishing(input));
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
    }

    /**
     * Test para comprobar el caso de no encontrar el DiveDay asociado (caso que no se deberia de dar nunca)
     */
    @Test
    @Transactional
    void testCreateFishing_DiveDayNotFound() {
        InCreateFishing input = DataUtils.getInCreateFishing();
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        GeographicalLocationEntity geographicalLocationEntity = DataUtils.getGeographicalLocationSuccess();
        when(geographicalLocationRepositoryPort.findByNameAndSite(geographicalLocation)).thenReturn(geographicalLocationEntity);
        when(diveDayPort.findById(any())).thenThrow(new EntityNotFoundException("DiveDayEntity not found"));


        assertThrows(EntityNotFoundException.class, () -> fishingService.createFishing(input));

        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(diveDayPort, times(1)).findById(any());

    }

    /**
     * Test para comprobar si ocurre un error al guardar la entidad en la base de datos (no se deberia dar)
     */
    @Test
    @Transactional
    void testCreateFishing_FisingSaveError() {
        InCreateFishing input = DataUtils.getInCreateFishing();
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        GeographicalLocationEntity geographicalLocationEntity = DataUtils.getGeographicalLocationSuccess();
        when(geographicalLocationRepositoryPort.findByNameAndSite(geographicalLocation)).thenReturn(geographicalLocationEntity);
        when(diveDayPort.findById(any())).thenReturn(new DiveDayEntity());
        when(fishingPort.save(any())).thenThrow(new RuntimeException("DiveDayEntity"));

        assertThrows(RuntimeException.class, () -> fishingService.createFishing(input));
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(diveDayPort, times(1)).findById(anyInt());
        verify(fishingPort, times(1)).save(any(FishingEntity.class));
    }
}
