package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.fishing.OutCreateFishDay;
import com.record.DeepDiveRecord.entity.FishingEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FishingMapper {

    FishingEntity fromCoreToEntity(Fishing input);
    Fishing fromEntityToCore(FishingEntity input);
}
