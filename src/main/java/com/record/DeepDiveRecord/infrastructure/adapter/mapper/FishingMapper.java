package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.create.FishingDetails;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import org.apache.poi.ss.usermodel.Row;

public interface FishingMapper {

    FishingEntity fromRequestToEntity (InCreateFishing input);
    FishingDetails fromEntityToResponse(FishingEntity input);
    FishingEntity fromResponseToEntity(FishingDetails input);

    FishingEntity mapRowToFishingEntity(Row row);
}
