package com.record.DeepDiveRecord.core.ports.fishing;

import com.record.DeepDiveRecord.core.model.fishing.InUpdateFishingDay;
import com.record.DeepDiveRecord.core.model.fishing.OutUpdatedFishingDay;

public interface UpdateFishingDayPort {
    OutUpdatedFishingDay updateFishingDay(InUpdateFishingDay input);
}
