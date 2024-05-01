package com.record.DeepDiveRecord.core.ports.fishing;

import com.record.DeepDiveRecord.core.model.fishing.InDeleteFishingDay;
import com.record.DeepDiveRecord.core.model.fishing.OutDeleteFishingDay;

public interface DeleteFishingDayPort {
   OutDeleteFishingDay deleteFishingDay(InDeleteFishingDay input);
}
