package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.request.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.WindConditionResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.stereotype.Component;

@Component
public interface WindConditionsMapper {
    InGetDataWeek fromDiveDayEntity(DiveDayEntity input);
    WindConditionResponse responseFromEntity(WindConditionsEntity input);
}
