package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.domain.model.dto.port.wind_condition.FindDeepDiveDataByDays;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.data.domain.Page;

public interface WindConditionsPort {
    Page<WindConditionsEntity> getDeepDiveDataByDays(FindDeepDiveDataByDays in);

}
