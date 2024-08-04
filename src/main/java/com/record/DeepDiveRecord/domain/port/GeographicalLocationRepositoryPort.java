package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;

import java.util.List;

public interface GeographicalLocationRepositoryPort {
    GeographicalLocationEntity findByNameAndSite(String name, String site);

    List<GeographicalLocationEntity> getAllGeGeographicalLocation();
}