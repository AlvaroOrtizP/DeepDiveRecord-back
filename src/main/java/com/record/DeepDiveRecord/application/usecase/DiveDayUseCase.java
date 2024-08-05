package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.diveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.DiveDayResponse;

public interface DiveDayUseCase {
    Integer createDiveDay(InCreateDailyDiving inCreateDailyDiving);
    DiveDayResponse findDiveDayById(Integer id);
}
