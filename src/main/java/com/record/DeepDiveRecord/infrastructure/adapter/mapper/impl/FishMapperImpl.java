package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import org.springframework.stereotype.Component;

@Component
public class FishMapperImpl implements FishMapper {
    @Override
    public Fish modelFromRequest(FishCreateRequest input) {
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
}
