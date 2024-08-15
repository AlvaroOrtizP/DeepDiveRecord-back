package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.FishingUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishingMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
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
    private GeograficLocationMapper geograficLocationMapper;
    @Autowired
    private FishingPort fishingPort;
    @Autowired
    private FishingMapper fishingMapper;
    @Override
    public Integer createFishing(InCreateFishing input) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicio del método createFishing con los datos de entrada: {}", input);

        // Comprobación de la geolocalización asociada con la solicitud de pesca
        LOGGER.info("Buscando la geolocalización correspondiente al nombre y sitio proporcionados.");
        GeographicalLocationEntity location = geographicalLocationRepositoryPort.findByNameAndSite(geograficLocationMapper.fromRequestToDtoFind(input));

        if (location != null) {
            LOGGER.info("Geolocalización encontrada: {}", location);
        } else {
            LOGGER.warn("No se encontró ninguna geolocalización que coincida con el nombre y sitio proporcionados.");
        }

        // Mapeo de la solicitud de pesca a la entidad FishingEntity
        FishingEntity fishingEntity = fishingMapper.fromRequestToEntity(input);
        LOGGER.info("Se ha mapeado la solicitud de pesca a la entidad FishingEntity.");

        // Asignación de la geolocalización a la entidad FishingEntity
        fishingEntity.setGeographicalLocation(location);
        LOGGER.info("Se ha asignado la geolocalización a la entidad de pesca.");

        // Guardar la entidad de pesca en el repositorio y obtener el ID resultante
        Integer savedFishingId = fishingPort.save(fishingEntity);
        LOGGER.info("Se ha guardado la entidad de pesca con ID: {}", savedFishingId);
        LOGGER.info("--------------------------------------------------------------------------------------------");

        // Retornar el ID de la entidad de pesca guardada
        return savedFishingId;
    }
}

