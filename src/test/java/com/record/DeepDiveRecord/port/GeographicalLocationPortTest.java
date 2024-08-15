package com.record.DeepDiveRecord.port;

import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl.GeographicalLocationRepositoryImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.geographical_location.GeographicalLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GeographicalLocationPortTest {
    @Mock
    private GeographicalLocationRepository geographicalLocationRepository;

    @InjectMocks
    private GeographicalLocationRepositoryImpl geographicalLocationRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByNameAndSiteSuccess() {
        // Given
        FindGeographicalLocation findGeographicalLocation = new FindGeographicalLocation("TestName", "TestSite");
        GeographicalLocationEntity entity = new GeographicalLocationEntity();
        entity.setName("TestName");
        entity.setSite("TestSite");

        when(geographicalLocationRepository.findAll()).thenReturn(List.of(entity));

        // When
        GeographicalLocationEntity result = geographicalLocationRepositoryImpl.findByNameAndSite(findGeographicalLocation);

        // Then
        assertNotNull(result);
        assertEquals("TestName", result.getName());
        assertEquals("TestSite", result.getSite());
        verify(geographicalLocationRepository, times(1)).findAll();
    }

    @Test
    void testFindByNameAndSiteNotFound() {
        // Given
        FindGeographicalLocation findGeographicalLocation = new FindGeographicalLocation("TestName", "TestSite");

        when(geographicalLocationRepository.findAll()).thenReturn(List.of());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> {
            geographicalLocationRepositoryImpl.findByNameAndSite(findGeographicalLocation);
        });
        verify(geographicalLocationRepository, times(1)).findAll();
    }

    @Test
    void testGetAllGeGeographicalLocationSuccess() {
        // Given
        GeographicalLocationEntity entity1 = new GeographicalLocationEntity();
        GeographicalLocationEntity entity2 = new GeographicalLocationEntity();

        when(geographicalLocationRepository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        // When
        List<GeographicalLocationEntity> result = geographicalLocationRepositoryImpl.getAllGeGeographicalLocation();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(geographicalLocationRepository, times(1)).findAll();
    }

    @Test
    void testGetAllGeGeographicalLocationEmpty() {
        // Given
        when(geographicalLocationRepository.findAll()).thenReturn(List.of());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            geographicalLocationRepositoryImpl.getAllGeGeographicalLocation();
        });
        verify(geographicalLocationRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        // Given
        GeographicalLocationEntity entity = new GeographicalLocationEntity();
        entity.setId(1);

        when(geographicalLocationRepository.findById(1)).thenReturn(Optional.of(entity));

        // When
        GeographicalLocationEntity result = geographicalLocationRepositoryImpl.findById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(geographicalLocationRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        when(geographicalLocationRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> {
            geographicalLocationRepositoryImpl.findById(1);
        });
        verify(geographicalLocationRepository, times(1)).findById(1);
    }
}
