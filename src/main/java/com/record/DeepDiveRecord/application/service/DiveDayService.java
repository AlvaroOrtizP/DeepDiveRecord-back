package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.DiveDayUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.*;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.model.exception.InvalidDiveDayDataException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.domain.port.TideTablePort;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.*;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.*;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    WindConditionsPort windConditionsPort;
    @Autowired
    GeographicalLocationPort geographicalLocationPort;

    DiveDayMapper diveDayMapper = new DiveDayMapperImpl();
    GeograficLocationMapper geograficLocationMapper = new GeograficLocationMapperImpl();
    TideTableMapper tideTableMapper = new TideTableMapperImpl();
    WindConditionsMapper windConditionsMapper = new WindConditionsMapperImpl();
    FishMapper fishMapper = new FishMapperImpl();


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
        if (!comprobarDatosCreateDiveDay(inCreateDailyDiving)) {
            throw new InvalidDiveDayDataException("Los datos para crear el DiveDay no son válidos.");
        }
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

    private boolean comprobarDatosCreateDiveDay(InCreateDailyDiving input) {
        // Expresión regular para verificar el formato NN:NN donde N es un número
        String regex = "\\d{2}:\\d{2}";

        if (input.getDay() == null || input.getDay().isBlank())
            return false;

        int day = Integer.parseInt(input.getDay());
        if (day > 366 || day < 1)
            return false;

        if (input.getMonth() == null || input.getMonth().isBlank())
            return false;

        int month = Integer.parseInt(input.getMonth());
        if (month > 12 || month < 1)
            return false;

        if (input.getYear() == null || input.getYear().isBlank() || input.getYear().length() != 4)
            return false;

        if (input.getBeginning() == null || input.getBeginning().isBlank() || !input.getBeginning().contains(":")) {
            return false;
        } else {
            //Comprobamos que el valor es numerico
            if (!input.getBeginning().matches(regex)) {
                return false;
            }
            // Eliminar el ':' y verificar si los caracteres restantes son numéricos
            String numericPart = input.getBeginning().replace(":", "");
            if (!numericPart.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }


        if (input.getEnd() == null || input.getEnd().isBlank() || !input.getEnd().contains(":")) {
            return false;
        } else {
            //Comprobamos que el valor es numerico
            if (!input.getEnd().matches(regex)) {
                return false;
            }
            // Eliminar el ':' y verificar si los caracteres restantes son numéricos
            String numericPart = input.getEnd().replace(":", "");
            if (!numericPart.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }


        if (input.getAssessment() == null || input.getAssessment() > 5 || input.getAssessment() < 1)
            return false;

        if (input.getJellyfish() == null || input.getJellyfish() > 3 || input.getJellyfish() < 1)
            return false;

        if (input.getVisibility() == null || input.getVisibility() > 4 || input.getVisibility() < 1)
            return false;

        if (input.getSeaBackground() == null || input.getSeaBackground() > 4 || input.getSeaBackground() < 1)
            return false;

        if (input.getFishGrass() == null || input.getFishGrass() > 3 || input.getFishGrass() < 1)
            return false;

        if (input.getPresencePlastic() == null || input.getPresencePlastic() > 3 || input.getPresencePlastic() < 1)
            return false;
        return true;
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
        res.setTideTableResponse(addTideTableToResponse(diveDayEntity));
        res.setWindConditionsList(addWindConditionsToResponse(diveDayEntity));
        res.setFishingList(addFishingListToResponse(diveDayEntity));

        LOGGER.info("--------------------------------------------------------------------------------------------");
        return res;
    }


    // Método privado para agregar los datos de la tabla de mareas a la respuesta
    private TideTableResponse addTideTableToResponse(DiveDayEntity diveDayEntity) {
        LOGGER.info("Se procede a buscar los datos de la tabla de mareas para el DiveDayEntity.");
        Optional<TideTableEntity> optionalTideTable = tideTablePort.findById(tideTableMapper.dtoPortFromDiveDayEntity(diveDayEntity));

        if (optionalTideTable.isEmpty()) {
            LOGGER.warn("No se encontraron datos de la tabla de mareas para el día de buceo.");
            return new TideTableResponse();
        } else {
            LOGGER.info("Se encontraron datos de la tabla de mareas y se han mapeado en la respuesta.");
            return tideTableMapper.responseFromEntity(optionalTideTable.get());
        }
    }

    // Método privado para agregar los datos meteorológicos a la respuesta
    private List<WindConditionResponse> addWindConditionsToResponse(DiveDayEntity diveDayEntity) {
        LOGGER.info("Se procede a buscar los datos meteorológicos para el DiveDayEntity.");
        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsPort.getDeepDiveDataByDays(windConditionsMapper.fromDiveDayEntityToDtoFindDeepData(diveDayEntity), false);
        List<WindConditionResponse> windConditionList = new ArrayList<>();
        for (WindConditionsEntity item : windConditionsEntityPage.getContent()) {
            windConditionList.add(windConditionsMapper.responseFromEntity(item));
        }

        LOGGER.info("Se encontraron {} registros de condiciones meteorológicas para el día de buceo.", windConditionList.size());
        return windConditionList;
    }

    // Método privado para agregar la lista de pesca a la respuesta
    private List<FishingResponse> addFishingListToResponse(DiveDayEntity diveDayEntity) {
        LOGGER.info("Se procede a buscar la lista de pescados para el DiveDayEntity.");
        List<FishingResponse> fishingList = new ArrayList<>();
        for (FishingEntity item : diveDayEntity.getFishingEntities()) {
            fishingList.add(fishMapper.responseFromEntity(item));
        }

        LOGGER.info("Se encontraron {} registros de pescados para el día de buceo.", fishingList.size());
        return fishingList;
    }

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


    public Page<DiveDayResponse> findByFilters(String zona, Pageable pageable) {
        Page<DiveDayEntity> diveDays = diveDayPort.findByFilters(zona, pageable);
        if(diveDays==null){
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
        return diveDays.map(this::mapToResponse);
    }

    private DiveDayResponse mapToResponse(DiveDayEntity diveDay) {
        return diveDayMapper.mapToResponse(diveDay);
    }


}
