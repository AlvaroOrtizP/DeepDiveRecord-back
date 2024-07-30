package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.fish.getallfishes.FishResponse;
import com.record.DeepDiveRecord.entity.FishEntity;
import org.springframework.stereotype.Component;

@Component
public interface FishMapper {
    FishResponse fromEntity(FishEntity input);
}
