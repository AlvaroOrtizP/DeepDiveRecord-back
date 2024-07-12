package com.record.DeepDiveRecord.service.adapters.fishing;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.ports.fishing.CreateFishingDayPort;
import com.record.DeepDiveRecord.entity.DiveDayAndFishingEntity;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.repository.FishRepository;
import com.record.DeepDiveRecord.repository.FishingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateFishingDayAdapter implements CreateFishingDayPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateFishingDayAdapter.class);

    private final FishingMapper fishingMapper;
    private final FishingRepository fishingRepository;
    private final FishRepository fishRepository;

    @Autowired
    public CreateFishingDayAdapter(FishingRepository fishingRepository, FishRepository fishRepository, FishingMapper fishingMapper) {
        this.fishingRepository = fishingRepository;
        this.fishRepository = fishRepository;
        this.fishingMapper = fishingMapper;
    }

    /**
     * Se crea el registro del dia de pesca y en caso de error se cancela el proceso
     * @param diveDayEntity Entidad del dia de buceo
     * @param fishingRequests Lista de pesca
     * @return Registro de la relacion creada
     * @throws RuntimeException Se lanza excepcion en caso de no encontrar pez con ese identificador
     */
    @Override
    @Transactional
    public List<DiveDayAndFishingEntity> createFishingDay(DiveDayEntity diveDayEntity, List<Fishing> fishingRequests) throws RuntimeException {
        LOGGER.info("Comienza createFishingDay");

        List<DiveDayAndFishingEntity> diveDayAndFishingList = new ArrayList<>();

        //Recorremos la lista de pesca
        for (Fishing fishingRequest : fishingRequests) {
            FishingEntity fishingEntity = new FishingEntity();

            //Mapeamos los datos para obtener la entidad
            fishingEntity = fishingMapper.fromCoreToEntity(fishingRequest);

            //Buscamos si existe un registro con el identificador del pez
            fishingEntity.setFish(fishRepository.findById(fishingRequest.getFishId()).orElseThrow(() -> new RuntimeException("Fish not found")));

            //Se crea el registro del dia de pesca
            FishingEntity savedFishing = fishingRepository.save(fishingEntity);

            //Guardamos los datos de las relaciones
            DiveDayAndFishingEntity diveDayAndFishingEntity = new DiveDayAndFishingEntity();
            diveDayAndFishingEntity.setFishing(savedFishing);
            diveDayAndFishingEntity.setDiveDay(diveDayEntity);

            //Guardamos en la lista
            diveDayAndFishingList.add(diveDayAndFishingEntity);
        }
        //Devolvemos la lista
        LOGGER.info("Finaliza createFishingDay");
        return diveDayAndFishingList;
    }
}
