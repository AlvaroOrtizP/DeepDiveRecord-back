package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;

public interface DiveDayUseCase {
    Integer createDiveDay(InCreateDailyDiving inCreateDailyDiving);
    DiveDayResponse findDiveDayById(Integer id);
}
