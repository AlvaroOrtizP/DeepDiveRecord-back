package com.record.DeepDiveRecord.core.windconditions.usecase.getdatabydays;

import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.InDataZoneRangeWC;
import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.OutDailyStatistics;
import org.springframework.data.domain.Page;

public interface GetWindConditionsByDaysUseCase {
    Page<OutDailyStatistics> getDeepDiveDataByDays(InDataZoneRangeWC in, int page, int size);
}
