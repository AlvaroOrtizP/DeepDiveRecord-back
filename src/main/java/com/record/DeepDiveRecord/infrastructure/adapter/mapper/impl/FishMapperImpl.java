package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.FishingResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import org.springframework.stereotype.Component;

@Component
public class FishMapperImpl implements FishMapper {
    @Override
    public Fish modelFromRequest(FishCreateRequest input) {
        Fish res = new Fish();

        return null;
    }

    @Override
    public FishEntity entityFromModel(Fish model) {
        return null;
    }

    @Override
    public Fish modelFromEntity(FishEntity entity) {
        return null;
    }

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
}
