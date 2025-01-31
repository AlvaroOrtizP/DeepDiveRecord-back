package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.fishing.FishingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FishingRepositoryImpl implements FishingPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingRepositoryImpl.class);


    @Autowired
    FishingRepository fishingRepository;
    @Override
    public Integer save(FishingEntity input) {
        LOGGER.info("Se procede a guardar {} ", input);
        FishingEntity res = fishingRepository.save(input);

        return res.getId();
    }

    @Override
    public FishingEntity getById(Integer id) {
        LOGGER.info("Se procede a obtener el registro {} ", id);
        Optional<FishingEntity> optional = fishingRepository.findById(id);
        if(optional.isPresent()){
            LOGGER.info("encontrada");
            return optional.get();
        }
        // Registro de error si no se encuentra la entidad y lanzamiento de excepción.

        LOGGER.info("No se encontro");
        throw new EntityNotFoundException("Fishing not found");
    }

    @Override
    public Integer update(FishingEntity input) {
        LOGGER.info("Se procede a guardar {} ", input);
        FishingEntity res =  fishingRepository.save(input);
        return res.getId();
    }
    @Override
    public void deleteByid(FishingEntity input) {
        LOGGER.info("Se procede a guardar {} ", input);
        fishingRepository.delete(input);
    }
}
