package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;

import java.util.List;

public interface FishPort {
    List<FishEntity> getAllFishList();
    FishEntity getFishById(Integer id);
}