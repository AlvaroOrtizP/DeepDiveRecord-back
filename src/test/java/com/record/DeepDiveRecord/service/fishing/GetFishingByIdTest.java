package com.record.DeepDiveRecord.service.fishing;

import com.record.DeepDiveRecord.application.service.FishingService;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.create.FishingDetails;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class GetFishingByIdTest {

    @Mock
    private FishingPort fishingPort;
    @InjectMocks
    private FishingService fishingService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetFishingById_Success() {
        when(fishingPort.getById(any(Integer.class))).thenReturn(DataUtils.getAFishingEntity());

        FishingDetails res = fishingService.getFishingById(1);
        assertEquals(res.getFishId(), 1);
    }

    @Test
    @Transactional
    void testGetFishingById_Failed() {

        when(fishingPort.getById(1)).thenThrow(new EntityNotFoundException("Fishing not found"));
        assertThrows(EntityNotFoundException.class, () -> fishingService.getFishingById(1));

    }
}
