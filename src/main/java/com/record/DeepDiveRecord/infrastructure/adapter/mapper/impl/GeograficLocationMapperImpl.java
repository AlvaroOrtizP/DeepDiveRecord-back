package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.UtilityMapper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeograficLocationMapperImpl implements GeograficLocationMapper {
    @Autowired
    private UtilityMapper utilityMapper;
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
    public FindGeographicalLocation fromRequestToDtoFind(String name, String site) {
        FindGeographicalLocation res = new FindGeographicalLocation();
        res.setName(name);
        res.setSite(site);
        return res;
    }

    @Override
    public GeographicalLocationEntity mapRowToGeographicalLocationEntity(Row row) {
        GeographicalLocationEntity entity = new GeographicalLocationEntity();
        entity.setId(utilityMapper.getCellValueAsInteger(row.getCell(0)));
        entity.setName(utilityMapper.getCellValueAsString(row.getCell(1)));
        entity.setSite(utilityMapper.getCellValueAsString(row.getCell(2)));
        entity.setIdWindwuru(utilityMapper.getCellValueAsString(row.getCell(3)));

        return entity;
    }

}
