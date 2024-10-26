package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.FishingDetails;
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
        res.setSightingTime(input.getSightingTime());
        DiveDayEntity diveDay = new DiveDayEntity();
        diveDay.setDiveDayId(input.getIdDiveDay());
        res.setDiveDay(diveDay);
        return res;
    }

    @Override
    public FishingDetails fromEntityToResponse(FishingEntity input) {
        FishingDetails res = new FishingDetails();
        res.setId(input.getId());
        res.setNotes(input.getNotes());
        res.setCaught(input.isCaught());
        res.setWeight(input.getWeight());
        res.setLatG(input.getLatG());
        res.setLogG(input.getLongG());
        res.setFishId(input.getFish().getId());
        res.setName(input.getFish().getName());
        res.setSite(input.getFish().getSite());
        res.setFirstSighting(input.getFish().getFirstSighting());
        res.setFirstLast(input.getFish().getFirstLast());
        res.setStartSeason(input.getFish().getStartSeason());
        res.setEndSeason(input.getFish().getEndSeason());
        res.setFirstLifeWarning(input.getFish().getFirstLifeWarning());
        res.setGeographieId(input.getGeographicalLocation().getId());
        res.setGeographieName(input.getGeographicalLocation().getName());
        res.setGeographieSite(input.getGeographicalLocation().getSite());
        return res;
    }
}
