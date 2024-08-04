package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;
import com.record.DeepDiveRecord.domain.model.dto.response.GeographicalLocationResponse;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationRepositoryPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.GeographicalLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeographicalLocationRepositoryImpl implements GeographicalLocationRepositoryPort {

    @Autowired
    private GeographicalLocationRepository geographicalLocationRepository;

    @Override
    public GeographicalLocationEntity findByNameAndSite(String name, String site) {
        return geographicalLocationRepository.findByNameAndSite(name, site);
    }

    @Override
    public List<GeographicalLocationEntity> getAllGeGeographicalLocation() {
        return geographicalLocationRepository.findAll();
    }
}