package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.FishingUseCase;
import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishingMapper;
import com.record.DeepDiveRecord.infrastructure.rest.controller.FishingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishingService implements FishingUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingService.class);

    @Autowired
    private GeographicalLocationPort geographicalLocationRepositoryPort;
    @Autowired
    private FishingPort fishingPort;
    @Autowired
    private FishingMapper fishingMapper;
    @Override
    public Integer createFishing(InCreateFishing input) {
        LOGGER.info("Comienzaa createFishing con el valor {} " , input);
        GeographicalLocationEntity location = geographicalLocationRepositoryPort.findByNameAndSite(input.getName(), input.getSite());
        if (location == null) {
            LOGGER.error("Error al encontrar localizacion");
            throw new IllegalArgumentException("Geographical location not found");
        }
        LOGGER.info("Geolocalizacion correcta ");
        FishingEntity res = fishingMapper.fromRequestToEntity(input);
        res.setGeographicalLocation(location);
        return fishingPort.save(res);
    }
}
