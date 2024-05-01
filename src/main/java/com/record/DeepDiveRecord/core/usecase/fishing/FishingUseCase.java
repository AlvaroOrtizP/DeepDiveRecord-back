package com.record.DeepDiveRecord.core.usecase.fishing;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.fishing.*;

public interface FishingUseCase {
    OutUpdatedFishingDay updateFishingDayPort(InUpdateFishingDay input);
    OutDeleteFishingDay deleteFishingDayPort(InDeleteFishingDay input);
    OutCreateFishDay createFishingDayPort(Fishing fishing);

}
