package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;

public interface FishingMapper {

    FishingEntity fromRequestToEntity (InCreateFishing input);
}
