package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import org.springframework.stereotype.Component;

@Component
public interface GeograficLocationMapper {
    GeographicalLocationResponse mapFromEntity (GeographicalLocationEntity input);
    com.record.DeepDiveRecord.domain.model.dto.response.dive_day.GeographicalLocationResponse responseFromDiveDayEntity(DiveDayEntity input);
    FindGeographicalLocation fromRequestToDtoFind(InCreateFishing input);

}