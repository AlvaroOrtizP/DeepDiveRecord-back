package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.fishing.createfishing.InCreateFishing;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import org.springframework.stereotype.Component;

@Component
public class FishingMapperImpl implements FishingMapper {
    @Override
    public FishingEntity fromApiDomain(InCreateFishing input) {
        FishingEntity res = new FishingEntity();
        res.setNotes(input.getNotes());
        res.setCaught(input.isCaught());
        res.setWeight(input.getWeight());
        FishEntity fishEntity = new FishEntity();
        fishEntity.setId(input.getFishId());
        res.setFish(fishEntity);

        res.setLatG(input.getLatG());
        res.setLatG(input.getLatG());

        DiveDayEntity diveDay = new DiveDayEntity();
        diveDay.setDiveDayId(input.getIdDiveDay());
        res.setDiveDay(diveDay);

        return res;
    }
}
