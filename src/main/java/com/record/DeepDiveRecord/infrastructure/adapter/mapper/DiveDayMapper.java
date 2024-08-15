package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import org.springframework.stereotype.Component;

@Component
public interface DiveDayMapper {
    DiveDayEntity entityFromResponse(InCreateDailyDiving input);
    DiveDayResponse responseFromEntity(DiveDayEntity input);
}
