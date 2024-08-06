package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.domain.model.dto.request.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.data.domain.Page;

public interface WindConditionsPort {
    Page<WindConditionsEntity> getDeepDiveDataByDays(InGetDataWeek in);//TODO tiene que venir la entidad
}
