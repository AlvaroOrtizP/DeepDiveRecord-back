package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public interface DiveDayMapper {
    DiveDayEntity entityFromResponse(InCreateDailyDiving input);
    DiveDayDetailsResponse responseFromEntity(DiveDayEntity input);
    DiveDayResponse mapToResponse(DiveDayEntity diveDay);

    DiveDayEntity mapRowToDiveDayEntity(Row row);
}
