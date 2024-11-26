package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.FishingDetails;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import org.apache.poi.ss.usermodel.Row;

public interface FishingMapper {

    FishingEntity fromRequestToEntity (InCreateFishing input);
    FishingDetails fromEntityToResponse(FishingEntity input);

    FishingEntity mapRowToFishingEntity(Row row);
}
