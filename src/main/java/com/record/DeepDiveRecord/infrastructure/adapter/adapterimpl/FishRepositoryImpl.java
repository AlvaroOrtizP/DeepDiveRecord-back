package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.fish.FishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FishRepositoryImpl implements FishPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishRepositoryImpl.class);

    @Autowired
    private FishRepository fishRepository;

    @Autowired
    private FishMapper fishMapper;

    @Override
    public List<FishEntity> getAllFishList() {
        return fishRepository.findAll();
    }

    @Override
    public FishEntity getFishById(Integer id) {
        LOGGER.info("Se porcede a buscar FishEntity por el id {}", id);

        Optional<FishEntity> fish = fishRepository.findById(id);
        // Verificación si la entidad está presente y retorno si es así.
        if(fish.isPresent()){
            LOGGER.info("encontrada");
            return fish.get();
        }
        // Registro de error si no se encuentra la entidad y lanzamiento de excepción.

        LOGGER.info("No se encontro");
        throw new EntityNotFoundException("Fish not found");
    }
}