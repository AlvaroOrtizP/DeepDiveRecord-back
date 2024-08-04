package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.response.GeographicalLocationResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import org.springframework.stereotype.Component;

@Component
public interface GeograficLocationMapper {
    GeographicalLocationResponse mapFromEntity (GeographicalLocationEntity input);
}
