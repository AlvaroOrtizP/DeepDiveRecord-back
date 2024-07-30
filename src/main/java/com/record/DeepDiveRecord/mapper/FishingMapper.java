package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.fishing.createfishing.InCreateFishing;
import com.record.DeepDiveRecord.entity.FishingEntity;

public interface FishingMapper {
    FishingEntity fromApiDomain(InCreateFishing input);
}
