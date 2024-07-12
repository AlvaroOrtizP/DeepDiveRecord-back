package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.fishing.*;
import com.record.DeepDiveRecord.core.ports.fishing.CreateFishingDayPort;
import com.record.DeepDiveRecord.core.ports.fishing.DeleteFishingDayPort;
import com.record.DeepDiveRecord.core.ports.fishing.UpdateFishingDayPort;
import com.record.DeepDiveRecord.core.usecase.fishing.FishingUseCase;
import org.springframework.stereotype.Service;

@Service
public class FishingService implements FishingUseCase {
    CreateFishingDayPort createFishingDayPort;
    DeleteFishingDayPort deleteFishingDayPort;
    UpdateFishingDayPort updateFishingDayPort;

    public FishingService(CreateFishingDayPort createFishingDayPort,
                          DeleteFishingDayPort deleteFishingDayPort,
                          UpdateFishingDayPort updateFishingDayPort) {
        this.createFishingDayPort = createFishingDayPort;
        this.deleteFishingDayPort = deleteFishingDayPort;
        this.updateFishingDayPort = updateFishingDayPort;

    }

    @Override
    public OutUpdatedFishingDay updateFishingDayPort(InUpdateFishingDay input) {
        // 0 Añadir filtros

        // 1 Añadir parametros estaticos

        // 2 llamada al port
        return updateFishingDayPort.updateFishingDay(input);
    }

    @Override
    public OutDeleteFishingDay deleteFishingDayPort(InDeleteFishingDay input) {
        // 0 Añadir filtros

        // 1 Añadir parametros estaticos

        // 2 llamada al port
        return deleteFishingDayPort.deleteFishingDay(input);
    }

}
