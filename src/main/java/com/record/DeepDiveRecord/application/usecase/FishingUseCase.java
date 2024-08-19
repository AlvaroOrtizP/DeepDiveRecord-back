package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.FishingDetails;

public interface FishingUseCase {
    Integer createFishing(InCreateFishing inCreateFishing);

    FishingDetails getFishingById(Integer id);
}
