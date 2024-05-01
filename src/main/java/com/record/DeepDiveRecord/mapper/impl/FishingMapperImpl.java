package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.fishing.OutCreateFishDay;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FishingMapperImpl implements FishingMapper {

    @Override
    public FishingEntity fromCoreToEntity(Fishing input) {

        FishingEntity res = new FishingEntity();
        res.setPescado(input.isPescado());
        res.setName(input.getName());
        res.setWeight(input.getWeight());
        res.setApuntes(input.getApuntes());

        return res;
    }


}
