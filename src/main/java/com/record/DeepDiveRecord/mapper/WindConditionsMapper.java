package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.core.model.windconditions.InDataDeleteWC;
import com.record.DeepDiveRecord.core.model.windconditions.OutDailyStatistics;
import com.record.DeepDiveRecord.core.model.windconditions.OutDeteleWindConditions;
import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.entity.WindConditionsId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;
@Component
public interface WindConditionsMapper {
    WindConditionsEntity getWindConditionsFromJson(Map.Entry<String, Map<String, String>> entry, String yearFile, String monthFile, String dayFile) throws ParseException;

    OutDailyStatistics mapToOutDailyStatistics(WindConditionsEntity entity);

    Page<OutDailyStatistics> mapToOutDailyStatisticsPage(Page<WindConditionsEntity> page);

    WindConditionsId mapToWindConditionsIdFromInDataDeleteWC(InDataDeleteWC in);

    OutDeteleWindConditions mapToOutDeteleWindConditionsFromInDataDeleteWC(InDataDeleteWC in, boolean comprobante, String error);
}
