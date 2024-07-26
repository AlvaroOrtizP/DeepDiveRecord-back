package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.api.domain.geograficlocation.GeographicalLocationResponse;
import com.record.DeepDiveRecord.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.repository.GeographicalLocationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeographicalLocationService {
    GeographicalLocationRepository geographicalLocationRepository;

    public GeographicalLocationService(GeographicalLocationRepository geographicalLocationRepository) {
        this.geographicalLocationRepository = geographicalLocationRepository;
    }

    public List<GeographicalLocationResponse> getAllGeGeographicalLocation() {

        List<GeographicalLocationEntity> geographicalLocationList = geographicalLocationRepository.findAll();

        List<GeographicalLocationResponse> res = new ArrayList<>();
        for (GeographicalLocationEntity item : geographicalLocationList){
            GeographicalLocationResponse geographicalLocationResponse = new GeographicalLocationResponse();
            geographicalLocationResponse.setId(item.getId());
            geographicalLocationResponse.setName(item.getName());
            geographicalLocationResponse.setSite(item.getSite());
            geographicalLocationResponse.setIdWindwuru(item.getIdWindwuru());
            res.add(geographicalLocationResponse);
        }
        return res;
    }
}
