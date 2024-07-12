package com.record.DeepDiveRecord.core.ports.fish;

import com.record.DeepDiveRecord.entity.FishEntity;

public interface GetFishByIdPort {
    FishEntity getFishById(Integer input);
}
