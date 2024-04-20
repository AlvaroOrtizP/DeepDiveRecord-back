package com.record.DeepDiveRecord.core.windconditions.usecase.deletewindconditionsbydays;


import com.record.DeepDiveRecord.core.windconditions.domain.deletewindconditionsbydays.InDataZoneRangeDeleteWC;
import com.record.DeepDiveRecord.core.windconditions.domain.deletewindconditionsbydays.OutDeteleWindConditions;

import java.util.List;

public interface DeleteWindConditionsByDaysUseCase {
    List<OutDeteleWindConditions> getDeepDiveDataByDays(InDataZoneRangeDeleteWC in);

}
