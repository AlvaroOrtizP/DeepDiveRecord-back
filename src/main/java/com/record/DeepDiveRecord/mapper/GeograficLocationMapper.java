package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.geograficlocation.GeographicalLocationResponse;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import org.springframework.stereotype.Component;

@Component
public interface GeograficLocationMapper {
    GeographicalLocationResponse responseFromDiveDayEntity(DiveDayEntity input);
}
