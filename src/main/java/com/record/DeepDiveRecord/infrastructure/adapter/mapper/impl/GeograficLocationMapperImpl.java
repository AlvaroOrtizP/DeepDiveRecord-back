package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.response.GeographicalLocationResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import org.springframework.stereotype.Component;

@Component
public class GeograficLocationMapperImpl implements GeograficLocationMapper {
    @Override
    public GeographicalLocationResponse mapFromEntity(GeographicalLocationEntity input) {
        return null;
    }
}
