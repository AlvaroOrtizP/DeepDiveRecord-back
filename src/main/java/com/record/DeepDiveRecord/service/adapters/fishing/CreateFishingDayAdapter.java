package com.record.DeepDiveRecord.service.adapters.fishing;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.core.model.fishing.OutCreateFishDay;
import com.record.DeepDiveRecord.core.ports.fishing.CreateFishingDayPort;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.repository.FishingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateFishingDayAdapter implements CreateFishingDayPort {
    FishingMapper mapper;
    FishingRepository fishingRepository;
    public CreateFishingDayAdapter(FishingMapper mapper,  FishingRepository fishingRepository){
        this.mapper = mapper;
        this.fishingRepository = fishingRepository;
    }

    @Override
    public OutCreateFishDay createFishingDay(Fishing input) {
        FishingEntity entity = mapper.fromCoreToEntity(input);
        try {
            fishingRepository.save(entity);

            return new OutCreateFishDay(input, new Checker(true, "CORRECT"));

        } catch (Exception e) {
            return new OutCreateFishDay(input, new Checker(true, "ERROR " + e.getMessage()));
        }
    }
}
