package com.record.DeepDiveRecord.service.adapters.fishing;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.fishing.InDeleteFishingDay;
import com.record.DeepDiveRecord.core.model.fishing.OutDeleteFishingDay;
import com.record.DeepDiveRecord.core.ports.fishing.DeleteFishingDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.repository.FishingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteFishingDayAdapter implements DeleteFishingDayPort {
    FishingRepository repository;
    public DeleteFishingDayAdapter( FishingRepository repository){
        this.repository = repository;
    }
    @Override
    public OutDeleteFishingDay deleteFishingDay(InDeleteFishingDay input) {

        try {
            Optional<FishingEntity> optionalEntity =  repository.findById(input.getId());

            optionalEntity.ifPresent(fishingEntity -> repository.delete(fishingEntity));

            return new OutDeleteFishingDay(input.getId(), input.getDay(), input.getMonth(), input.getYear(), input.getSite(), new Checker(true, "CORRECT"));
        }
        catch (Exception e){
            return new OutDeleteFishingDay(input.getId(), input.getDay(), input.getMonth(), input.getYear(), input.getSite(), new Checker(false, "ERROR: "+ e.getMessage()));
        }

    }
}
