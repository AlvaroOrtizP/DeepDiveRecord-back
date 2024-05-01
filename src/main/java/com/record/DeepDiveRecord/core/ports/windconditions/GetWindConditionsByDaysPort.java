package com.record.DeepDiveRecord.core.ports.windconditions;

import com.record.DeepDiveRecord.core.model.windconditions.InDataZoneRangeWC;
import com.record.DeepDiveRecord.core.model.windconditions.OutDailyStatistics;
import org.springframework.data.domain.Page;

public interface GetWindConditionsByDaysPort {
    Page<OutDailyStatistics> getDeepDiveDataByDays(InDataZoneRangeWC in, int page, int size);
}
