package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;

public interface FishingPort {
    Integer save(FishingEntity input);
}
