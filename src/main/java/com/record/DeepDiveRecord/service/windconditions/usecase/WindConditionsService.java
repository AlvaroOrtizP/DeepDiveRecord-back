package com.record.DeepDiveRecord.service.windconditions.usecase;

import com.record.DeepDiveRecord.core.windconditions.domain.deletewindcondition.InDataDeleteWC;
import com.record.DeepDiveRecord.core.windconditions.domain.deletewindcondition.OutDeteleWindConditions;
import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.InDataZoneRangeWC;
import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.OutDailyStatistics;
import com.record.DeepDiveRecord.core.windconditions.usecase.deletewindcondition.DeleteWindConditionsUseCase;
import com.record.DeepDiveRecord.core.windconditions.usecase.getdatabydays.GetWindConditionsByDaysUseCase;
import org.springframework.data.domain.Page;

public class WindConditionsService {
    GetWindConditionsByDaysUseCase getWindConditionsByDaysUseCase;
    DeleteWindConditionsUseCase deleteWindConditionsUseCase;

    public WindConditionsService(GetWindConditionsByDaysUseCase getWindConditionsByDaysUseCase, DeleteWindConditionsUseCase deleteWindConditionsUseCase){
        this.getWindConditionsByDaysUseCase = getWindConditionsByDaysUseCase;
        this.deleteWindConditionsUseCase = deleteWindConditionsUseCase;
    }
    public Page<OutDailyStatistics> getDeepDiveDataByDays(InDataZoneRangeWC in, int page, int size) {
        return getWindConditionsByDaysUseCase.getDeepDiveDataByDays(in, page, size);
    }
    public OutDeteleWindConditions deleteWindCondition(InDataDeleteWC in) {
        return deleteWindConditionsUseCase.deleteWindCondition(in);
    }
}
