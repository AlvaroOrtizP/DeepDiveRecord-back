package com.record.DeepDiveRecord.service.fishing;

import com.record.DeepDiveRecord.application.service.FishingService;
import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.edit.InEditFishing;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.model.exception.InvalidFishingDataException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.util.DataUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class EditFishingTest {
    @Mock
    private FishingPort fishingPort;
    @Mock
    private FishPort fishPort;
    @InjectMocks
    private FishingService fishingService;
    @Mock
    private DiveDayPort diveDayPort;
    @Mock
    private GeographicalLocationPort geographicalLocationRepositoryPort;
    @Test
    @Transactional
    void testCreateFishing_Success() {
        InEditFishing input = DataUtils.getInEditFishing();

        FishingEntity entity = DataUtils.getAFishingEntity();
        when(fishingPort.getById(any())).thenReturn(entity);
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        GeographicalLocationEntity geographicalLocationEntity = DataUtils.getGeographicalLocationSuccess();
        when(geographicalLocationRepositoryPort.findByNameAndSite(geographicalLocation)).thenReturn(geographicalLocationEntity);
        when(fishPort.getFishById(input.getFishId())).thenReturn(DataUtils.getFishEntitySuccess());
        when(diveDayPort.findById(any())).thenReturn(new DiveDayEntity());
        when(fishingPort.update(any())).thenReturn(2);

        Integer res = fishingService.editFishing(input);

        assertEquals(2, res, "El ID del Fishing debería coincidir");
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(fishPort, times(1)).getFishById(any());
        verify(diveDayPort, times(1)).findById(any());
        verify(fishingPort, times(1)).update(any(FishingEntity.class));
        assertNotEquals(input.getFishId(), entity.getFish().getId());
        assertNotEquals(input.isCaught(), entity.isCaught());
        assertNotEquals(input.getName(), entity.getGeographicalLocation().getName());
        assertNotEquals(input.getSite(), entity.getGeographicalLocation().getSite());
        assertNotEquals(input.getNotes(), entity.getNotes());
        assertNotEquals(input.getWeight(), entity.getWeight());
        assertNotEquals(input.getLatG(), entity.getLatG());
        assertNotEquals(input.getLongG(), entity.getLongG());
        
    }
    @Test
    @Transactional
    void testCreateFishing_Success2() {
        InEditFishing input = DataUtils.getInEditFishing();

        FishingEntity entity = DataUtils.getAFishingEntity();
        when(fishingPort.getById(any())).thenReturn(entity);
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        GeographicalLocationEntity geographicalLocationEntity = DataUtils.getGeographicalLocationSuccess();
        when(geographicalLocationRepositoryPort.findByNameAndSite(geographicalLocation)).thenReturn(geographicalLocationEntity);
        when(fishPort.getFishById(input.getFishId())).thenReturn(DataUtils.getFishEntitySuccess());
        when(diveDayPort.findById(any())).thenReturn(new DiveDayEntity());
        when(fishingPort.update(any())).thenReturn(2);

        input.setFishingId(entity.getFish().getId());
        input.setCaught(entity.isCaught());
        input.setName(entity.getGeographicalLocation().getName());
        input.setSite(entity.getGeographicalLocation().getSite());
        input.setNotes(entity.getNotes());
        input.setWeight(entity.getWeight());
        input.setLatG(entity.getLatG());
        input.setLongG(entity.getLongG());
        Integer res = fishingService.editFishing(input);

        assertEquals(2, res, "El ID del Fishing debería coincidir");
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(fishPort, times(1)).getFishById(any());
        verify(diveDayPort, times(1)).findById(any());
        verify(fishingPort, times(1)).update(any(FishingEntity.class));
        assertEquals(input.getFishId(), 2);
        assertEquals(input.isCaught(), entity.isCaught());
        assertEquals(input.getName(), entity.getGeographicalLocation().getName());
        assertEquals(input.getSite(), entity.getGeographicalLocation().getSite());
        assertEquals(input.getNotes(), entity.getNotes());
        assertEquals(input.getWeight(), entity.getWeight());
        assertEquals(input.getLatG(), entity.getLatG());
        assertEquals(input.getLongG(), entity.getLongG());
    }

    /**
     * Test para comprobar que no encontramos el Fishing que queremos editar
     * Este caso no se deberia dar nunca
     */
    @Test
    @Transactional
    void testCreateFishing_Failed_FishingNotFound() {
        InEditFishing input = DataUtils.getInEditFishing();
        when(fishingPort.getById(any())).thenThrow(new EntityNotFoundException("Fishing not found"));

        assertThrows(EntityNotFoundException.class, () -> fishingService.editFishing(input));
        verify(fishingPort, times(1)).getById(any());
        verify(geographicalLocationRepositoryPort, times(0)).findByNameAndSite(any());
        verify(fishPort, times(0)).getFishById(any());
        verify(diveDayPort, times(0)).findById(any());
    }
    /**
     * Test para comprobar que no encontramos el geographicalLocation que queremos editar
     * Este caso no se deberia dar nunca
     */
    @Test
    @Transactional
    void testCreateFishing_FailedGeographicalNotFound() {
        InEditFishing input = DataUtils.getInEditFishing();

        FishingEntity entity = DataUtils.getAFishingEntity();
        when(fishingPort.getById(any())).thenReturn(entity);
        when(geographicalLocationRepositoryPort.findByNameAndSite(any())).thenThrow(new EntityNotFoundException("Geographical location not found"));

        assertThrows(EntityNotFoundException.class, () -> fishingService.editFishing(input));
        verify(fishingPort, times(1)).getById(any());
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(fishPort, times(0)).getFishById(any());
        verify(diveDayPort, times(0)).findById(any());
    }

    /**
     * Test para comprobar que no encontramos el Fish que queremos editar
     * Este caso no se deberia dar nunca
     */
    @Test
    @Transactional
    void testCreateFishing_FailedFishNotFound() {
        InEditFishing input = DataUtils.getInEditFishing();

        FishingEntity entity = DataUtils.getAFishingEntity();
        when(fishingPort.getById(any())).thenReturn(entity);
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        GeographicalLocationEntity geographicalLocationEntity = DataUtils.getGeographicalLocationSuccess();
        when(geographicalLocationRepositoryPort.findByNameAndSite(geographicalLocation)).thenReturn(geographicalLocationEntity);
        when(fishPort.getFishById(any())).thenThrow(new EntityNotFoundException("Fish not found"));


        assertThrows(EntityNotFoundException.class, () -> fishingService.editFishing(input));
        verify(fishingPort, times(1)).getById(any());
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(fishPort, times(1)).getFishById(any());
        verify(diveDayPort, times(0)).findById(any());
    }

    /**
     * Test para comprobar que no encontramos el Dive que queremos editar
     * Este caso no se deberia dar nunca
     */
    @Test
    @Transactional
    void testCreateFishing_FailedDiveNotFound() {
        InEditFishing input = DataUtils.getInEditFishing();

        FishingEntity entity = DataUtils.getAFishingEntity();
        when(fishingPort.getById(any())).thenReturn(entity);
        FindGeographicalLocation geographicalLocation = new FindGeographicalLocation();
        geographicalLocation.setName(input.getName());
        geographicalLocation.setSite(input.getSite());
        GeographicalLocationEntity geographicalLocationEntity = DataUtils.getGeographicalLocationSuccess();
        when(geographicalLocationRepositoryPort.findByNameAndSite(geographicalLocation)).thenReturn(geographicalLocationEntity);
        when(fishPort.getFishById(input.getFishId())).thenReturn(DataUtils.getFishEntitySuccess());
        when(fishingPort.update(any())).thenReturn(2);
        when(diveDayPort.findById(any())).thenThrow(new EntityNotFoundException("DiveDayEntity not found"));



        assertThrows(EntityNotFoundException.class, () -> fishingService.editFishing(input));
        verify(fishingPort, times(1)).getById(any());
        verify(geographicalLocationRepositoryPort, times(1)).findByNameAndSite(any());
        verify(fishPort, times(1)).getFishById(any());
        verify(diveDayPort, times(1)).findById(any());
    }

    @Test
    @Transactional
    void testCreateFishing_FaildedInvalidInput() {
        InEditFishing invalidInput = new InEditFishing();

        // Verificar el resultado y las interacciones con fishing null
        assertThatThrownBy(() -> fishingService.editFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");

        invalidInput.setIdDiveDay(1);
        // Verificar el resultado y las interacciones con diveDayId null
        assertThatThrownBy(() -> fishingService.editFishing(invalidInput))
                .isInstanceOf(InvalidFishingDataException.class)
                .hasMessageContaining("Los datos para crear el Fishing no son válidos.");
    }
}
