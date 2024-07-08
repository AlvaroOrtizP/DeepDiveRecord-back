package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.windconditions.InDeepDiveLogger;
import com.record.DeepDiveRecord.api.domain.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.api.domain.windconditions.OutGetData;
import com.record.DeepDiveRecord.core.model.windconditions.*;
import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.entity.WindConditionsId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
@Component
public interface WindConditionsMapper {
    WindConditionsEntity getWindConditionsFromJson(Map.Entry<String, Map<String, String>> entry, String yearFile, String monthFile, String dayFile) throws ParseException;

    OutDailyStatistics mapToOutDailyStatistics(WindConditionsEntity entity);

    Page<OutDailyStatistics> mapToOutDailyStatisticsPage(Page<WindConditionsEntity> page);

    WindConditionsId mapToWindConditionsIdFromInDataDeleteWC(InDataDeleteWC in);

    OutDeteleWindConditions mapToOutDeteleWindConditionsFromInDataDeleteWC(InDataDeleteWC in, boolean comprobante, String error);

    InDataZoneRangeWC mapToInDataZoneRangeWCFromInGetDataWeek (InGetDataWeek inGetDataWeek);

    InForecast fromDomainToCore(InDeepDiveLogger deepDiveLogger);


    List<OutGetData> mapOutDailyStatisticsListToOutGetDataList (List<OutDailyStatistics> outDailyStatisticsList);
}
