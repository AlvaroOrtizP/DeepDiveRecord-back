package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.WindConditionResponse;
import com.record.DeepDiveRecord.api.domain.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import org.springframework.stereotype.Component;

@Component
public interface WindConditionsMapper {
    InGetDataWeek fromDiveDayEntity(DiveDayEntity input);
    WindConditionResponse responseFromEntity(WindConditionsEntity input);

}
