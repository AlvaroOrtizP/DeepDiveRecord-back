package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.diveday.creatediveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.DiveDayResponse;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import org.springframework.stereotype.Component;

@Component
public interface DiveDayMapper {
    DiveDayResponse responseFromEntity(DiveDayEntity input);

    DiveDayEntity entityFromResponse(InCreateDailyDiving input);
}
