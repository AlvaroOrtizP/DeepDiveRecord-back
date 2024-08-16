package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.port.wind_condition.FindDeepDiveDataByDays;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.WindConditionResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetData;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.stereotype.Component;

@Component
public interface WindConditionsMapper {
    WindConditionResponse responseFromEntity(WindConditionsEntity input);
    FindDeepDiveDataByDays fromDiveDayEntityToDtoFindDeepData(DiveDayEntity input);
    FindDeepDiveDataByDays fromInGetDataWeekToDtoFindDeepData(InGetDataWeek input);
    OutGetData getOutGetData(WindConditionsEntity item);
}
