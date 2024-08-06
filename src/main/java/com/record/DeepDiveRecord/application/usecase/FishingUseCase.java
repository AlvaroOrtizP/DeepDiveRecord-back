package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;

public interface FishingUseCase {
    Integer createFishing(InCreateFishing inCreateFishing);
}
