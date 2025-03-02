package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.edit.InEditFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.create.FishingDetails;

public interface FishingUseCase {
    Integer createFishing(InCreateFishing inCreateFishing);

    FishingDetails getFishingById(Integer id);

    Integer editFishing(InEditFishing input);
    void deleteFishingById(Integer id);
}
