package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.fish.getallfishes.FishResponse;
import com.record.DeepDiveRecord.entity.FishEntity;
import com.record.DeepDiveRecord.mapper.FishMapper;
import org.springframework.stereotype.Component;

@Component
public class FishMapperImpl implements FishMapper {
    @Override
    public FishResponse fromEntity(FishEntity input) {
        FishResponse res = new FishResponse();
        res.setId(input.getId());
        res.setName(input.getName());
        res.setSite(input.getSite());
        res.setFirstSighting(input.getFirstSighting());
        res.setFirstLast(input.getFirstLast());
        res.setStartSeason(input.getStartSeason());
        res.setEndSeason(input.getEndSeason());
        res.setFirstLifeWarning(input.getFirstLifeWarning());
        return res;
    }
}
