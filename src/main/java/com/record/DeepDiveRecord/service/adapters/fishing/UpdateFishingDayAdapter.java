package com.record.DeepDiveRecord.service.adapters.fishing;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.OutUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.fishing.InUpdateFishingDay;
import com.record.DeepDiveRecord.core.model.fishing.OutUpdatedFishingDay;
import com.record.DeepDiveRecord.core.ports.fishing.UpdateFishingDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import com.record.DeepDiveRecord.repository.FishingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateFishingDayAdapter implements UpdateFishingDayPort {
    FishingMapper mapper;
    FishingRepository repository;
    public UpdateFishingDayAdapter(FishingMapper mapper, FishingRepository repository){
        this.mapper = mapper;
        this.repository = repository;

    }

    @Override
    public OutUpdatedFishingDay updateFishingDay(InUpdateFishingDay input) {
        try {
            FishingEntity entity = mapper.fromCoreToEntity(input.getFishingNew());
            repository.save(entity);
            return new OutUpdatedFishingDay(input.getFishingNew(), new Checker(true, "CORRECT"));
        } catch (Exception e) {
            return new OutUpdatedFishingDay(input.getFishingNew(), new Checker(false, "ERROR: " + e.getMessage()));
        }

    }
}
