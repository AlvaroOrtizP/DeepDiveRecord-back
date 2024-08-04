package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.windconditions.OutGetDataList;

public interface WindConditionsUseCase {
    OutGetDataList getDeepDiveDataByDays(InGetDataWeek input);
}
