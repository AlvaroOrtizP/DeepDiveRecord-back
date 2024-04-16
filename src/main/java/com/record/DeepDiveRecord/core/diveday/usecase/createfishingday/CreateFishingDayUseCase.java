package com.record.DeepDiveRecord.core.diveday.usecase.createfishingday;

import com.record.DeepDiveRecord.core.diveday.domain.diveday.FishingDay;

public interface CreateFishingDayUseCase {
    boolean createFishingDay(FishingDay fishingDay);
}
