package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.response.diveday.FishingResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import org.springframework.stereotype.Component;

@Component
public class FishMapperImpl implements FishMapper {


    @Override
    public FishingResponse responseFromEntity(FishingEntity input) {
        FishingResponse fishingResponse = new FishingResponse();
        fishingResponse.setId(input.getId());
        fishingResponse.setCaught(input.isCaught());
        fishingResponse.setWeight(input.getWeight());
        fishingResponse.setNotes(input.getNotes());
        fishingResponse.setLatG(input.getLatG());
        fishingResponse.setLongG(input.getLongG());
        fishingResponse.setName(input.getGeographicalLocation().getName());
        fishingResponse.setSite(input.getGeographicalLocation().getSite());
        fishingResponse.setNameFish(input.getFish().getName());
        return fishingResponse;
    }

    @Override
    public FishResponse fromEntity(FishEntity input) {
        FishResponse res = new FishResponse();
        res.setEndSeason(input.getEndSeason());
        res.setSite(input.getSite());
        res.setId(input.getId());
        res.setName(input.getName());
        res.setFirstLast(input.getFirstLast());
        res.setFirstSighting(input.getFirstSighting());
        res.setStartSeason(input.getStartSeason());
        res.setFirstLifeWarning(input.getFirstLifeWarning());
        return res;
    }
}
