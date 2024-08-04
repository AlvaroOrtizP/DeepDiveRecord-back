package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;

public interface FishUseCase {
    Integer createFish(FishCreateRequest input);
}
