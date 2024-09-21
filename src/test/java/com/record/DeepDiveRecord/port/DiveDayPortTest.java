package com.record.DeepDiveRecord.port;

import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl.DiveDayRepositoryImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.dive_day.DiveDayCustomRepository;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.dive_day.DiveDayRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
class DiveDayPortTest {
    @Mock
    private DiveDayRepository diveDayRepository;
    @Mock
    DiveDayCustomRepository diveDayCustomRepository;
    @InjectMocks
    private DiveDayRepositoryImpl diveDayRepositoryImpl;
    private DiveDayEntity mockDiveDayEntity;

    @BeforeEach
    void setUp() {
        mockDiveDayEntity = new DiveDayEntity();
        mockDiveDayEntity.setDiveDayId(1);
    }

    @Test
    void testFindById_Success() {
        // Simula el comportamiento del repositorio
        when(diveDayRepository.findById(1)).thenReturn(Optional.of(mockDiveDayEntity));

        // Llama al método y verifica el resultado
        DiveDayEntity result = diveDayRepositoryImpl.findById(1);
        Assertions.assertNotNull(result);
        assertEquals(mockDiveDayEntity.getDiveDayId(), result.getDiveDayId());

        // Verifica que se haya llamado al método findById con el ID correcto
        verify(diveDayRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        // Simula el comportamiento del repositorio cuando no se encuentra el registro
        when(diveDayRepository.findById(1)).thenReturn(Optional.empty());

        // Verifica que se lanza la excepción cuando no se encuentra el ID
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> diveDayRepositoryImpl.findById(1));
        assertEquals("DiveDayEntity not found with id 1", exception.getMessage());

        // Verifica que se haya llamado al método findById con el ID correcto
        verify(diveDayRepository, times(1)).findById(1);
    }

    @Test
    @Transactional
    void testSave() {
        // Simula el comportamiento del repositorio al guardar la entidad
        when(diveDayRepository.save(any(DiveDayEntity.class))).thenReturn(mockDiveDayEntity);

        // Llama al método y verifica el resultado
        DiveDayEntity result = diveDayRepositoryImpl.save(mockDiveDayEntity);
        Assertions.assertNotNull(result);
        assertEquals(mockDiveDayEntity.getDiveDayId(), result.getDiveDayId());

        // Verifica que se haya llamado al método save con la entidad correcta
        verify(diveDayRepository, times(1)).save(mockDiveDayEntity);
    }


    @Test
    void testFindByFilters_Success() {
        // Arrange
        String zona = "lugar x";
        Pageable pageable = PageRequest.of(0, 10);
        DiveDayEntity diveDayEntity = new DiveDayEntity();
        Page<DiveDayEntity> expectedPage = new PageImpl<>(Collections.singletonList(diveDayEntity));

        when(diveDayCustomRepository.findDiveDaysByFilters(zona, pageable)).thenReturn(expectedPage);

        // Act
        Page<DiveDayEntity> result = diveDayRepositoryImpl.findByFilters(zona, pageable);

        // Assert
        assertEquals(expectedPage, result);
    }
    @Test
    void testFindByFilters_NoResults() {
        // Arrange
        String zona = "Atlantic";
        Pageable pageable = PageRequest.of(0, 10);
        Page<DiveDayEntity> emptyPage = new PageImpl<>(Collections.emptyList());

        when(diveDayCustomRepository.findDiveDaysByFilters(zona, pageable)).thenReturn(emptyPage);

        // Act
        Page<DiveDayEntity> result = diveDayRepositoryImpl.findByFilters(zona, pageable);

        // Assert
        assertEquals(0, result.getTotalElements());
        assertEquals(emptyPage, result);
    }
}
