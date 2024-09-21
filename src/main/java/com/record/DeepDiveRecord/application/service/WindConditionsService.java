package com.record.DeepDiveRecord.application.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.record.DeepDiveRecord.application.usecase.WindConditionsUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.common.Pagination;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetData;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.CommonMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.WindConditionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WindConditionsService implements WindConditionsUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsService.class);

    @Autowired
    WindConditionsPort windConditionsPort;
    @Autowired
    WindConditionsMapper windConditionsMapper;
    @Autowired
    CommonMapper commonMapper;

    @Override
    public OutGetDataList getDeepDiveDataByDays(InGetDataWeek input) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicia el método getDeepDiveDataByDays con el input: {}", input);

        // Obtener datos de condiciones de viento
        Page<WindConditionsEntity> windConditionsEntityPage = fetchWindConditionsData(input, true);

        // Procesar datos obtenidos y preparar la respuesta
        OutGetDataList response = prepareOutGetDataList(windConditionsEntityPage);

        LOGGER.info("Finaliza el método getDeepDiveDataByDays.");
        LOGGER.info("--------------------------------------------------------------------------------------------");

        // Devolver la respuesta completa
        return response;
    }

    /**
     * Método privado para obtener los datos de condiciones de viento de la base de datos.
     */
    private Page<WindConditionsEntity> fetchWindConditionsData(InGetDataWeek input, boolean onlyImpares) {
        LOGGER.info("Obteniendo los datos de condiciones de viento para la semana especificada.");
        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsPort.getDeepDiveDataByDays(
                windConditionsMapper.fromInGetDataWeekToDtoFindDeepData(input), onlyImpares
        );
        LOGGER.info("Se han obtenido {} entidades de WindConditions del repositorio.", windConditionsEntityPage.getTotalElements());
        return windConditionsEntityPage;
    }

    /**
     * Método privado para preparar la lista de resultados y la paginación en la respuesta.
     */
    private OutGetDataList prepareOutGetDataList(Page<WindConditionsEntity> windConditionsEntityPage) {
        LOGGER.info("Preparando la lista de resultados y la paginación para la respuesta.");

        OutGetDataList response = new OutGetDataList();
        List<OutGetData> outGetDataList = new ArrayList<>();

        // Mapear cada entidad WindConditions a un objeto OutGetData
        for (WindConditionsEntity item : windConditionsEntityPage.getContent()) {
            OutGetData outGetData = windConditionsMapper.getOutGetData(item);
            outGetDataList.add(outGetData);
        }
        LOGGER.info("Se han mapeado {} entidades WindConditions a objetos OutGetData.", outGetDataList.size());

        // Configurar la lista de resultados y la paginación en la respuesta
        response.setOutGetDataList(outGetDataList);
        Pagination pagination = commonMapper.getPagination(windConditionsEntityPage);
        response.setPagination(pagination);

        LOGGER.info("Se ha configurado la paginación de la respuesta.");
        return response;
    }



}
