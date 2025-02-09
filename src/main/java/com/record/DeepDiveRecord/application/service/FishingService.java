package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.FishingUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.edit.InEditFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.create.FishingDetails;
import com.record.DeepDiveRecord.domain.model.exception.InvalidFishingDataException;
import com.record.DeepDiveRecord.domain.model.util.Validator;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishingMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.GeograficLocationMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl.FishingMapperImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl.GeograficLocationMapperImpl;
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
    private FishPort fishPort;
    @Autowired
    private DiveDayPort diveDayPort;
    FishingMapper fishingMapper = new FishingMapperImpl();
    GeograficLocationMapper geograficLocationMapper = new GeograficLocationMapperImpl();

    @Override
    public Integer createFishing(InCreateFishing input) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicio del método createFishing con los datos de entrada: {}", input);

        if (!comprobarDatosInCreateFishing(input)) {
            throw new InvalidFishingDataException("Los datos para crear el Fishing no son válidos.");
        }
        // Comprobación de la geolocalización asociada con la solicitud de pesca
        LOGGER.info("Buscando la geolocalización correspondiente al nombre y sitio proporcionados.");
        GeographicalLocationEntity location = geographicalLocationRepositoryPort.findByNameAndSite(geograficLocationMapper.fromRequestToDtoFind(input.getName(), input.getSite()));

        //Comprobamos que existe el diveDay. En caso de no encontrar lanzamos excepcion
        diveDayPort.findById(input.getIdDiveDay());

        //Se ajusta para la equivalencia con el otro mapa
        // input.setLatG(input.getLatG() + 1);
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

    private boolean comprobarDatosInCreateFishing(InCreateFishing input) {
        // Expresión regular para verificar el formato NN:NN donde N es un número
        if (Validator.validateInteger(input.getFishId())) {
            return false;
        }
        if (Validator.validateString(input.getName())) {
            return false;
        }
        if (Validator.validateString(input.getSite())) {
            return false;
        }
        if (Validator.validateString(input.getNotes())) {
            return false;
        }
        if (Validator.validateBigDecimal(input.getWeight())) {
            return false;
        }
        if (Validator.validateDouble(input.getLatG())) {
            return false;
        }
        if (Validator.validateDouble(input.getLongG())) {
            return false;
        }
        if (Validator.validateInteger(input.getIdDiveDay())) {
            return false;
        }
        if (Validator.validateHora(input.getSightingTime())) {
            return false;
        }
        return true;
    }

    @Override
    public FishingDetails getFishingById(Integer id) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicio del método getFishingById con el identificador: {}", id);
        FishingEntity entity = fishingPort.getById(id);
        FishingDetails res = fishingMapper.fromEntityToResponse(entity);
        LOGGER.info("Se obtiene con valior {} ", res);

        LOGGER.info("--------------------------------------------------------------------------------------------");
        return res;
    }


    @Override
    public Integer editFishing(InEditFishing input) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicio del método editFishing con los datos de entrada: {}", input);

        if (Validator.validateInteger(input.getFishingId())) {
            throw new InvalidFishingDataException("Los datos para crear el Fishing no son válidos.");
        }
        if (Validator.validateInteger(input.getIdDiveDay())) {
            throw new InvalidFishingDataException("Los datos para crear el Fishing no son válidos.");
        }


        // Obtener los detalles actuales de la entidad de pesca utilizando el ID proporcionado
        FishingDetails existingDetails = getFishingById(input.getFishingId());

        // Mapear los detalles existentes a la entidad para actualizarla
        FishingEntity fishingEntity = fishingMapper.fromResponseToEntity(existingDetails);

        // Actualizar los campos de la entidad con los nuevos valores del input
        LOGGER.info("Actualizando la entidad de pesca con los nuevos valores.");

        if (!Validator.validateString(input.getName()))
            fishingEntity.setNotes(input.getNotes());
        if (!Validator.validateBigDecimal(input.getWeight()))
            fishingEntity.setWeight(input.getWeight());
        if (!Validator.validateHora(input.getSightingTime()))
            fishingEntity.setSightingTime(input.getSightingTime());
        if (!Validator.validateDouble(input.getLongG()))
            fishingEntity.setLongG(input.getLongG());
        if (!Validator.validateDouble(input.getLatG()))
            fishingEntity.setLatG(input.getLatG());
        fishingEntity.setCaught(input.isCaught());


        // Buscar la geolocalización actualizada
        LOGGER.info("Buscando la geolocalización correspondiente al nombre y sitio proporcionados.");
        GeographicalLocationEntity location = geographicalLocationRepositoryPort.findByNameAndSite(
                geograficLocationMapper.fromRequestToDtoFind(input.getName(), input.getSite())
        );
        fishingEntity.setGeographicalLocation(location);

        // Buscar el fish
        LOGGER.info("Buscando el fish correspondiente al id.");
        FishEntity fishEntity = fishPort.getFishById(input.getFishId());
        fishingEntity.setFish(fishEntity);


        // Buscar el dive day
        LOGGER.info("Buscando el dia correspondiente al id.");
        DiveDayEntity diveDayentity = diveDayPort.findById(input.getIdDiveDay());
        fishingEntity.setDiveDay(diveDayentity);


        // Guardar los cambios en el puerto del repositorio
        Integer id = fishingPort.update(fishingEntity);
        LOGGER.info("Se ha actualizado la entidad de pesca con éxito. {} ", id);

        LOGGER.info("--------------------------------------------------------------------------------------------");
        return id;
    }

    @Override
    public void deleteFishingById(Integer id) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicio del método deleteFishingById con el identificador: {}", id);
        fishingPort.deleteByid(id);
        LOGGER.info("--------------------------------------------------------------------------------------------");
    }
}

