package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;

import java.util.List;
import java.util.Optional;

public interface GeographicalLocationPort {
    GeographicalLocationEntity findByNameAndSite(String name, String site);

    List<GeographicalLocationEntity> getAllGeGeographicalLocation();

    Optional<GeographicalLocationEntity> findById(Integer idGeograficLocation);
}