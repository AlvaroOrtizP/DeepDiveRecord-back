package com.record.DeepDiveRecord.service.diveday;

import com.record.DeepDiveRecord.core.model.common.DiveDay;
import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutUpdatedDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.UpdateDiveDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.mapper.impl.DiveDayMapperImpl;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import com.record.DeepDiveRecord.service.adapters.diveday.UpdateDiveDayAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateDiveDayAdapterTest {
    UpdateDiveDayPort updateDiveDayPort;

    @Mock
    DiveDayRepository repository;

    DiveDayMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new DiveDayMapperImpl();
        updateDiveDayPort = new UpdateDiveDayAdapter(mapper, repository);
    }

    @Test
    void givenExistingDiveDayThenReturnUpdatedDiveDay() {
        // Arrange
        InUpdatedDiveDay input = getInUpdatedDiveDay();
        DiveDayEntity entity = new DiveDayEntity();
        entity.setDiveDayId(1);


        // Configure mock repository behavior
        when(repository.findById(anyInt())).thenReturn(Optional.of(entity));
        when(repository.save(any(DiveDayEntity.class))).thenReturn(entity);

        // Act
        OutUpdatedDiveDay result = updateDiveDayPort.updatedDiveDay(input);

        // Assert
        assertTrue(result.getChecker().isChecker());
        assertEquals("CORRECT", result.getChecker().getMessage());
    }

    @Test
    void givenBadTimeRangeThenReturnIncorrectTimeRange() {
        // Arrange
        InUpdatedDiveDay input = getInUpdatedDiveDay();
        input.getDiveDayNew().setBeginning("12");

        // Act
        OutUpdatedDiveDay result = updateDiveDayPort.updatedDiveDay(input);

        // Assert
        assertFalse(result.getChecker().isChecker());
        assertEquals("INCORRECT TIME RANGE", result.getChecker().getMessage());
    }





    @Test
    void givenInvalidDiveDayIdThenReturnErrorMessage() {
        // Arrange
        InUpdatedDiveDay input = getInUpdatedDiveDay();
        input.getDiveDayNew().setDiveDayId(-1); // Dive day ID is invalid (negative)

        // Act
        OutUpdatedDiveDay result = updateDiveDayPort.updatedDiveDay(input);

        // Assert
        assertFalse(result.getChecker().isChecker());
        assertEquals("NOT FOUND", result.getChecker().getMessage());
    }


    @Test
    void givenExceptionWhenSavingEntityToRepositoryThenReturnErrorMessage() {
        // Arrange
        InUpdatedDiveDay input = getInUpdatedDiveDay();
        DiveDayEntity entity = new DiveDayEntity();
        when(repository.findById(anyInt())).thenReturn(Optional.of(entity));
        when(repository.save(any(DiveDayEntity.class))).thenThrow(new RuntimeException("Save Exception"));

        // Act
        OutUpdatedDiveDay result = updateDiveDayPort.updatedDiveDay(input);

        // Assert
        assertFalse(result.getChecker().isChecker());
        assertEquals("ERROR: Save Exception", result.getChecker().getMessage());
    }

    @Test
    void givenValidDiveDayEntityThenReturnSuccessMessage() {
        // Arrange
        InUpdatedDiveDay input = getInUpdatedDiveDay();
        DiveDayEntity entity = new DiveDayEntity();
        when(repository.findById(anyInt())).thenReturn(Optional.of(entity));

        // Act
        OutUpdatedDiveDay result = updateDiveDayPort.updatedDiveDay(input);

        // Assert
        assertTrue(result.getChecker().isChecker());
        assertEquals("CORRECT", result.getChecker().getMessage());
    }

    @Test
    void givenNonExistingDiveDayEntityThenReturnNotFoundMessage() {
        // Arrange
        InUpdatedDiveDay input = getInUpdatedDiveDay();
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        OutUpdatedDiveDay result = updateDiveDayPort.updatedDiveDay(input);

        // Assert
        assertFalse(result.getChecker().isChecker());
        assertEquals("NOT FOUND", result.getChecker().getMessage());
    }



    private InUpdatedDiveDay getInUpdatedDiveDay() {
        InUpdatedDiveDay input = new InUpdatedDiveDay();
        DiveDay diveDayNew = new DiveDay();
        diveDayNew.setDiveDayId(1);
        diveDayNew.setDay("12");
        diveDayNew.setMonth("02");
        diveDayNew.setYear("2024");
        diveDayNew.setBeginning("03");
        diveDayNew.setEnd("06");
        diveDayNew.setSite("La arena");
        diveDayNew.setFishingList(new ArrayList<>());
        input.setDiveDayNew(diveDayNew);
        return input;
    }

}