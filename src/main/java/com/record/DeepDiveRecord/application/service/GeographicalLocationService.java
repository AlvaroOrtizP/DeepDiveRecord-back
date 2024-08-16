package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.GeographicalLocationUseCase;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GeographicalLocationService implements GeographicalLocationUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeographicalLocationService.class);

    @Autowired
    private GeographicalLocationPort geographicalLocationRepositoryPort;
    @Autowired
    private GeograficLocationMapper geograficLocationMapper;
    @Override
    public List<GeographicalLocationResponse> getAllGeGeographicalLocation() {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicia el método getAllGeGeographicalLocation para obtener la lista de todas las ubicaciones geográficas.");

        // Obtener la lista de entidades GeographicalLocation desde el repositorio
        List<GeographicalLocationEntity> geograficEntityList = geographicalLocationRepositoryPort.getAllGeGeographicalLocation();
        LOGGER.info("Se han obtenido {} entidades de GeographicalLocation del repositorio.", geograficEntityList.size());

        // Mapear la lista de entidades GeographicalLocation a la lista de respuestas GeographicalLocationResponse
        List<GeographicalLocationResponse> res = new ArrayList<>();
        for (GeographicalLocationEntity item : geograficEntityList) {
            res.add(geograficLocationMapper.mapFromEntity(item));
        }
        LOGGER.info("Se han mapeado las entidades GeographicalLocation a {} objetos GeographicalLocationResponse.", res.size());
        LOGGER.info("--------------------------------------------------------------------------------------------");

        // Devolver la lista de respuestas GeographicalLocationResponse
        return res;
    }
}
