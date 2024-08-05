package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.request.diveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.DiveDayResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import org.springframework.stereotype.Component;

@Component
public interface DiveDayMapper {
    DiveDayEntity entityFromResponse(InCreateDailyDiving input);
    DiveDayResponse responseFromEntity(DiveDayEntity input);
}
