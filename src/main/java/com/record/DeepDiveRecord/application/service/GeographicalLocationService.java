package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.GeographicalLocationUseCase;
import com.record.DeepDiveRecord.domain.model.dto.response.geographicallocation.GeographicalLocationResponse;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GeographicalLocationService implements GeographicalLocationUseCase {
    @Autowired
    private GeographicalLocationPort geographicalLocationRepositoryPort;
    @Autowired
    private GeograficLocationMapper geograficLocationMapper;
    @Override
    public List<GeographicalLocationResponse> getAllGeGeographicalLocation() {
        List<GeographicalLocationEntity> geograficEntityList =  geographicalLocationRepositoryPort.getAllGeGeographicalLocation();
        if (geograficEntityList.isEmpty()) {
            throw new IllegalArgumentException("Geographical location not found");
        }
        List<GeographicalLocationResponse> res = new ArrayList<>();

        for(GeographicalLocationEntity item : geograficEntityList){
            GeographicalLocationResponse geographicalResponse = new GeographicalLocationResponse();
            geographicalResponse.setId(item.getId());
            geographicalResponse.setName(item.getName());
            geographicalResponse.setSite(item.getSite());
            geographicalResponse.setIdWindwuru(item.getIdWindwuru());
            res.add(geographicalResponse);
        }
        return res;
    }
}
