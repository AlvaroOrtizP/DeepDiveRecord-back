package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.DiveDayUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.*;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.domain.port.TideTablePort;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.*;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiveDayService implements DiveDayUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayService.class);
    @Autowired
    DiveDayPort diveDayPort;
    @Autowired
    TideTablePort tideTablePort;
    @Autowired
    DiveDayMapper diveDayMapper;
    @Autowired
    GeograficLocationMapper geograficLocationMapper;
    @Autowired
    TideTableMapper tideTableMapper;
    @Autowired
    WindConditionsPort windConditionsPort;
    @Autowired
    WindConditionsMapper windConditionsMapper;
    @Autowired
    FishMapper fishMapper;
    @Autowired
    GeographicalLocationPort geographicalLocationPort;

    /**
     * Crea un nuevo DiveDay a partir de los datos proporcionados en inCreateDailyDiving.
     *
     * @param inCreateDailyDiving Objeto con los datos de entrada para crear un nuevo día de buceo.
     * @return El ID del DiveDay creado.
     */
    @Override
    public Integer createDiveDay(InCreateDailyDiving inCreateDailyDiving) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Comienza el metodo createDiveDay con valor {}", inCreateDailyDiving);

        // Buscar la ubicación geográfica por ID y verificar su existencia.
        GeographicalLocationEntity location = geographicalLocationPort.findById(inCreateDailyDiving.getIdGeographicLocation());

        LOGGER.info("La marca geografica es correcta {}", location);
        // Mapear los datos de entrada a la entidad DiveDayEntity y asociar la ubicación y sitio.
        DiveDayEntity diveDayEntity = diveDayMapper.entityFromResponse(inCreateDailyDiving);
        diveDayEntity.setSite(location.getIdWindwuru());
        diveDayEntity.setGeographicalLocation(location);

        LOGGER.info("Se procede a guardar el dia de buceo");
        // Guardar el DiveDay en la base de datos
        DiveDayEntity savedDiveDay = diveDayPort.save(diveDayEntity);
        LOGGER.info("Se guarda el dia de buceo con id {}", savedDiveDay.getDiveDayId());
        LOGGER.info("--------------------------------------------------------------------------------------------");
        // Retornar el ID del nuevo DiveDay
        return savedDiveDay.getDiveDayId();
    }

    /**
     * Busca un DiveDay por su ID y devuelve los datos asociados.
     *
     * @param id El ID del día de buceo a buscar.
     * @return Un objeto DiveDayResponse con los detalles del DiveDay encontrado.
     * @throws EntityNotFoundException si no se encuentra el DiveDay con el ID proporcionado.
     */
    @Override
    public DiveDayDetailsResponse findDiveDayById(Integer id) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Comienza el metodo findDiveDayById con el ID: {}", id);

        // Buscar el DiveDay por su ID
        DiveDayEntity diveDayEntity = findDiveDayEntityById(id);

        // Construir la respuesta DiveDayResponse
        DiveDayDetailsResponse res = buildDiveDayResponse(diveDayEntity);

        // Agregar datos adicionales (TideTable, WindConditions, FishingList)
        addTideTableToResponse(diveDayEntity, res);
        addWindConditionsToResponse(diveDayEntity, res);
        addFishingListToResponse(diveDayEntity, res);

        LOGGER.info("--------------------------------------------------------------------------------------------");
        return res;
    }

    public Page<DiveDayResponse> findByFilters(String zona, Pageable pageable) {
        Page<DiveDayEntity> diveDays = diveDayPort.findByFilters(zona, pageable);
        return diveDays.map(this::mapToResponse);
    }

    private DiveDayResponse mapToResponse(DiveDayEntity diveDay) {
        return diveDayMapper.mapToResponse(diveDay);
    }

    /**
     * Metodo que devuelve todas
     *
     * @return
     */

    // Método privado para buscar el DiveDayEntity por su ID
    private DiveDayEntity findDiveDayEntityById(Integer id) {
        return diveDayPort.findById(id);
    }

    // Método privado para construir el DiveDayResponse inicial
    private DiveDayDetailsResponse buildDiveDayResponse(DiveDayEntity diveDayEntity) {
        DiveDayDetailsResponse res = diveDayMapper.responseFromEntity(diveDayEntity);
        res.setGeographicalLocationResponse(geograficLocationMapper.responseFromDiveDayEntity(diveDayEntity));
        LOGGER.info("Se ha mapeado la respuesta DiveDayResponse con los datos obtenidos.");
        return res;
    }

    // Método privado para agregar los datos de la tabla de mareas a la respuesta
    private void addTideTableToResponse(DiveDayEntity diveDayEntity, DiveDayDetailsResponse res) {
        LOGGER.info("Se procede a buscar los datos de la tabla de mareas para el DiveDayEntity.");
        Optional<TideTableEntity> optionalTideTable = tideTablePort.findById(tideTableMapper.dtoPortFromDiveDayEntity(diveDayEntity));

        if (optionalTideTable.isEmpty()) {
            LOGGER.warn("No se encontraron datos de la tabla de mareas para el día de buceo.");
            res.setTideTableResponse(new TideTableResponse());
        } else {
            LOGGER.info("Se encontraron datos de la tabla de mareas y se han mapeado en la respuesta.");
            res.setTideTableResponse(tideTableMapper.responseFromEntity(optionalTideTable.get()));
        }
    }

    // Método privado para agregar los datos meteorológicos a la respuesta
    private void addWindConditionsToResponse(DiveDayEntity diveDayEntity, DiveDayDetailsResponse res) {
        LOGGER.info("Se procede a buscar los datos meteorológicos para el DiveDayEntity.");
        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsPort.getDeepDiveDataByDays(windConditionsMapper.fromDiveDayEntityToDtoFindDeepData(diveDayEntity), false);
        List<WindConditionResponse> windConditionList = new ArrayList<>();
        for (WindConditionsEntity item : windConditionsEntityPage.getContent()) {
            windConditionList.add(windConditionsMapper.responseFromEntity(item));
        }
        res.setWindConditionsList(windConditionList);
        LOGGER.info("Se encontraron {} registros de condiciones meteorológicas para el día de buceo.", windConditionList.size());
    }

    // Método privado para agregar la lista de pesca a la respuesta
    private void addFishingListToResponse(DiveDayEntity diveDayEntity, DiveDayDetailsResponse res) {
        LOGGER.info("Se procede a buscar la lista de pescados para el DiveDayEntity.");
        List<FishingResponse> fishingList = new ArrayList<>();
        for (FishingEntity item : diveDayEntity.getFishingEntities()) {
            fishingList.add(fishMapper.responseFromEntity(item));
        }
        res.setFishingList(fishingList);
        LOGGER.info("Se encontraron {} registros de pescados para el día de buceo.", fishingList.size());
    }
}
