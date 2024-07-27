package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.geograficlocation.GeographicalLocationResponse;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.GeograficLocationMapper;
import org.springframework.stereotype.Component;

@Component
public class GeograficLocationMapperImpl implements GeograficLocationMapper {
    @Override
    public GeographicalLocationResponse responseFromDiveDayEntity(DiveDayEntity input) {
        GeographicalLocationResponse geograficLocationResponse = new GeographicalLocationResponse();
        geograficLocationResponse.setIdWindwuru(input.getGeographicalLocation().getIdWindwuru());
        geograficLocationResponse.setName(input.getGeographicalLocation().getName());
        geograficLocationResponse.setId(input.getGeographicalLocation().getId());
        geograficLocationResponse.setSite(input.getGeographicalLocation().getSite());
        return geograficLocationResponse;
    }
}
