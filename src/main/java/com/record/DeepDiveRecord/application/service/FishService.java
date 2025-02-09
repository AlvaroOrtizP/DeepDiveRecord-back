package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.FishUseCase;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl.FishMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishService implements FishUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishService.class);

    private FishMapper fishMapper = new FishMapperImpl();

    @Autowired
    private FishPort fishRepositoryPort;

    @Override
    public List<FishResponse> getAllFishList() {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicia el método getAllFishList para obtener la lista de todos los peces.");

        // Obtener la lista de entidades Fish desde el repositorio
        List<FishEntity> entityList = fishRepositoryPort.getAllFishList();
        LOGGER.info("Se han obtenido {} entidades de Fish del repositorio.", entityList.size());

        // Mapear la lista de entidades Fish a la lista de respuestas FishResponse
        List<FishResponse> res = new ArrayList<>();
        for (FishEntity item : entityList) {
            res.add(fishMapper.fromEntity(item));
        }
        LOGGER.info("Se han mapeado las entidades Fish a {} objetos FishResponse.", res.size());
        LOGGER.info("--------------------------------------------------------------------------------------------");

        // Devolver la lista de respuestas FishResponse
        return res;
    }
}