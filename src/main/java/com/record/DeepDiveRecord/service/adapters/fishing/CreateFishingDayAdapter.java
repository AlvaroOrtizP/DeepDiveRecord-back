package com.record.DeepDiveRecord.service.adapters.fishing;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.fishing.OutCreateFishDay;
import com.record.DeepDiveRecord.core.ports.fishing.CreateFishingDayPort;
import com.record.DeepDiveRecord.entity.FishEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.repository.FishRepository;
import com.record.DeepDiveRecord.repository.FishingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateFishingDayAdapter implements CreateFishingDayPort {
    private final FishingMapper mapper;
    private final FishingRepository fishingRepository;
    private final FishRepository fishRepository;

    @Autowired
    public CreateFishingDayAdapter(FishingMapper mapper, FishingRepository fishingRepository, FishRepository fishRepository) {
        this.mapper = mapper;
        this.fishingRepository = fishingRepository;
        this.fishRepository = fishRepository;
    }

    @Override
    @Transactional
    public OutCreateFishDay createFishingDay(Fishing input) {
        FishingEntity entity = mapper.fromCoreToEntity(input);
        try {
            // Verifica si la FishEntity ya está persistida, si no, persiste primero
            FishEntity fishEntity = entity.getFish();
            if (fishEntity.getId() == null || !fishRepository.existsById(fishEntity.getId())) {
                fishRepository.save(fishEntity);
            }

            fishingRepository.save(entity);
            return new OutCreateFishDay(input, new Checker(true, "CORRECT"));
        } catch (Exception e) {
            return new OutCreateFishDay(input, new Checker(false, "ERROR " + e.getMessage()));
        }
    }
}
