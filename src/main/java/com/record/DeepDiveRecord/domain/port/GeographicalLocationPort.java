package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;

import java.util.List;

public interface GeographicalLocationPort {
    GeographicalLocationEntity findByNameAndSite(FindGeographicalLocation findGeographicalLocation);

    List<GeographicalLocationEntity> getAllGeGeographicalLocation();

    GeographicalLocationEntity findById(Integer idGeograficLocation);
}