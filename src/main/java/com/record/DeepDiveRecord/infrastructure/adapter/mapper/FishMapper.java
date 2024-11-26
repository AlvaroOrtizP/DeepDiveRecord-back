package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.FishingResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public interface FishMapper {
    FishingResponse responseFromEntity(FishingEntity input);//TODO cambiar de sitio

    FishResponse fromEntity(FishEntity input);

    FishEntity mapRowToFishEntity(Row row);
}
