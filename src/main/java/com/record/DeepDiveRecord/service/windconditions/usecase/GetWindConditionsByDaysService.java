package com.record.DeepDiveRecord.service.windconditions.usecase;

import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.InDataZoneRangeWC;
import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.OutDailyStatistics;
import com.record.DeepDiveRecord.core.windconditions.usecase.getdatabydays.GetWindConditionsByDaysUseCase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetWindConditionsByDaysService {
    GetWindConditionsByDaysUseCase getWindConditionsByDaysUseCase;

    public GetWindConditionsByDaysService(GetWindConditionsByDaysUseCase getWindConditionsByDaysUseCase){
        this.getWindConditionsByDaysUseCase = getWindConditionsByDaysUseCase;
    }
    public Page<OutDailyStatistics> getDeepDiveDataByDays(InDataZoneRangeWC in, int page, int size) {
        return getWindConditionsByDaysUseCase.getDeepDiveDataByDays(in, page, size);
    }
}
