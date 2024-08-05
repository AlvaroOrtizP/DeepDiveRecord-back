package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.FishingResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import org.springframework.stereotype.Component;

@Component
public interface FishMapper {
    Fish modelFromRequest(FishCreateRequest input);
    FishEntity entityFromModel(Fish model);
    Fish modelFromEntity(FishEntity entity);
    FishingResponse responseFromEntity(FishingEntity input);


}
