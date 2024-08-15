package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;

public interface WindConditionsUseCase {
    OutGetDataList getDeepDiveDataByDays(InGetDataWeek input);
}
