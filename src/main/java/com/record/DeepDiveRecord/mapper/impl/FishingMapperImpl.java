package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.fishing.OutCreateFishDay;
import com.record.DeepDiveRecord.entity.FishEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FishingMapperImpl implements FishingMapper {

    @Override
    public FishingEntity fromCoreToEntity(Fishing input) {

        FishingEntity res = new FishingEntity();
        res.setCaught(input.isCaught());
        FishEntity fish = new FishEntity();
        fish.setId(input.getFishId());
        res.setFish(fish);
        res.setWeight(input.getWeight());
        res.setNotes(input.getNotes());

        return res;
    }

    @Override
    public Fishing fromEntityToCore(FishingEntity input) {
        Fishing res = new Fishing();
        res.setCaught(input.isCaught());
        res.setFishId(1);
        res.setWeight(input.getWeight());
        res.setNotes(input.getNotes());
        return res;
    }


}
