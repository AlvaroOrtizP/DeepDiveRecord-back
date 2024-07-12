package com.record.DeepDiveRecord.core.ports.fishing;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.entity.DiveDayAndFishingEntity;
import com.record.DeepDiveRecord.entity.DiveDayEntity;

import java.util.List;

public interface CreateFishingDayPort {
    List<DiveDayAndFishingEntity> createFishingDay(DiveDayEntity diveDayEntity, List<Fishing> fishingList) throws RuntimeException;
}
