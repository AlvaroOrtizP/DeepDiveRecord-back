package com.record.DeepDiveRecord.core.ports.fishing;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.fishing.OutCreateFishDay;

import java.util.List;

public interface CreateFishingDayPort {
    OutCreateFishDay createFishingDay(Fishing input);
}
