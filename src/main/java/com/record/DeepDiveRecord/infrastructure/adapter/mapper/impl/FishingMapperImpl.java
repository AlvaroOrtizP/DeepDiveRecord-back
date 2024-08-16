package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishingMapper;
import org.springframework.stereotype.Component;

@Component
public class FishingMapperImpl implements FishingMapper {
    @Override
    public FishingEntity fromRequestToEntity(InCreateFishing input) {
        FishingEntity res = new FishingEntity();
        res.setCaught(input.isCaught());
        res.setNotes(input.getNotes());
        res.setWeight(input.getWeight());
        FishEntity fish = new FishEntity();
        fish.setId(input.getFishId());
        res.setFish(fish);
        res.setLatG(input.getLatG());
        res.setLongG(input.getLongG());
        DiveDayEntity diveDay = new DiveDayEntity();
        diveDay.setDiveDayId(input.getIdDiveDay());
        res.setDiveDay(diveDay);
        return res;
    }
}
