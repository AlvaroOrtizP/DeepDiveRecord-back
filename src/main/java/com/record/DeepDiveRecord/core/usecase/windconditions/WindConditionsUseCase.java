package com.record.DeepDiveRecord.core.usecase.windconditions;

import com.record.DeepDiveRecord.core.model.windconditions.*;
import org.springframework.data.domain.Page;

public interface WindConditionsUseCase {
    Page<OutDailyStatistics> getDeepDiveDataByDaysPort(InDataZoneRangeWC in, int page, int size);
    OutDeteleWindConditions deleteWindConditionPort(InDataDeleteWC in);
    OutForecast createWindCondition(InForecast deepDiveLogger);
}
