package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import org.springframework.stereotype.Component;

@Component
public class GeograficLocationMapperImpl implements GeograficLocationMapper {
    @Override
    public GeographicalLocationResponse mapFromEntity(GeographicalLocationEntity input) {
        GeographicalLocationResponse geograficLocationResponse = new GeographicalLocationResponse();
        geograficLocationResponse.setIdWindwuru(input.getIdWindwuru());
        geograficLocationResponse.setName(input.getName());
        geograficLocationResponse.setId(input.getId());
        geograficLocationResponse.setSite(input.getSite());
        return geograficLocationResponse;
    }

    @Override
    public com.record.DeepDiveRecord.domain.model.dto.response.dive_day.GeographicalLocationResponse responseFromDiveDayEntity(DiveDayEntity input) {
        com.record.DeepDiveRecord.domain.model.dto.response.dive_day.GeographicalLocationResponse geograficLocationResponse = new com.record.DeepDiveRecord.domain.model.dto.response.dive_day.GeographicalLocationResponse();
        geograficLocationResponse.setIdWindwuru(input.getGeographicalLocation().getIdWindwuru());
        geograficLocationResponse.setName(input.getGeographicalLocation().getName());
        geograficLocationResponse.setId(input.getGeographicalLocation().getId());
        geograficLocationResponse.setSite(input.getGeographicalLocation().getSite());
        return geograficLocationResponse;
    }

    @Override
    public FindGeographicalLocation fromRequestToDtoFind(InCreateFishing input) {
        FindGeographicalLocation res = new FindGeographicalLocation();
        res.setName(input.getName());
        res.setSite(input.getSite());
        return res;
    }

}