package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;

import java.util.List;

public interface FishUseCase {
    List<FishResponse> getAllFishList();
}
