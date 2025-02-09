package com.record.DeepDiveRecord.service.fishing;

import com.record.DeepDiveRecord.application.service.FishingService;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ActiveProfiles("test")
class DeleteFishingByIdTest {
    @Mock
    private FishingPort fishingPort;
    @InjectMocks
    private FishingService fishingService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDeleteFishingById_Success() {
        fishingService.deleteFishingById(1);
        verify(fishingPort, times(1)).deleteByid(any());
    }

}
