package com.record.DeepDiveRecord.service.adapters.windconditions;

import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.InDataZoneRangeWC;
import com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays.OutDailyStatistics;
import com.record.DeepDiveRecord.core.windconditions.usecase.getdatabydays.GetWindConditionsByDaysUseCase;
import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.mapper.WindConditionsMapper;
import com.record.DeepDiveRecord.repository.windconditions.WindConditionsCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class GetWindConditionsByDaysAdapter implements GetWindConditionsByDaysUseCase {
    WindConditionsMapper windConditionsMapper;
    @Autowired
    WindConditionsCustomRepository windConditionsCustomRepository;

    public GetWindConditionsByDaysAdapter( WindConditionsCustomRepository windConditionsCustomRepository){
        this.windConditionsCustomRepository = windConditionsCustomRepository;
    }
    @Override
    public Page<OutDailyStatistics> getDeepDiveDataByDays(InDataZoneRangeWC in, int page, int size) {
        Page<WindConditionsEntity> windConditionsEntityPage =  windConditionsCustomRepository.customWindConditionsSearch(in.getFromYear(), in.getFromMonth(), in.getFromDay(), in.getToYear(),
                in.getToMonth(), in.getFromDay(), in.getSite(),  PageRequest.of(page, size));

        return WindConditionsMapper.mapToOutDailyStatisticsPage(windConditionsEntityPage);
    }
}
