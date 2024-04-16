package com.record.DeepDiveRecord.core.forecast.usecase.getdatabydays;

import com.record.DeepDiveRecord.core.forecast.domain.getdatabydays.OutDailyStatistics;
import com.record.DeepDiveRecord.core.forecast.domain.getdatabydays.InDateZoneRange;

import java.util.List;

public interface GetDeepDiveDataByDaysUseCase {
    List<OutDailyStatistics> getDeepDiveDataByDays(InDateZoneRange in);
}
