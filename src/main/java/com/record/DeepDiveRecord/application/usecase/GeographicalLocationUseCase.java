package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;

import java.util.List;

public interface GeographicalLocationUseCase {
    List<GeographicalLocationResponse> getAllGeGeographicalLocation();
}
