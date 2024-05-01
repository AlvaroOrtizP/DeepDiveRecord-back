package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.core.model.diveday.*;
import com.record.DeepDiveRecord.core.ports.diveday.CreateDiveDayPort;
import com.record.DeepDiveRecord.core.ports.diveday.DeleteDiveDayPort;
import com.record.DeepDiveRecord.core.ports.diveday.UpdateDiveDayPort;
import com.record.DeepDiveRecord.core.usecase.diveday.DiveDayUseCase;
import org.springframework.stereotype.Service;

@Service
public class DiveDayService implements DiveDayUseCase {
    UpdateDiveDayPort updateDiveDayPort;
    DeleteDiveDayPort deleteDiveDayPort;
    CreateDiveDayPort createDiveDayPort;

    public DiveDayService(UpdateDiveDayPort updateDiveDayPort,
                          DeleteDiveDayPort deleteDiveDayPort,
                          CreateDiveDayPort createDiveDayPort) {
        this.updateDiveDayPort = updateDiveDayPort;
        this.deleteDiveDayPort = deleteDiveDayPort;
        this.createDiveDayPort = createDiveDayPort;
    }

    @Override
    public OutUpdatedDiveDay updatedDiveDayPort(InUpdatedDiveDay input) {
        return updateDiveDayPort.updatedDiveDay(input);

    }

    @Override
    public OutDeleteDiveDay deleteDiveDayPort(InDeleteDiveDay input) {
        return deleteDiveDayPort.deleteDiveDay(input);
    }

    @Override
    public OutCreateDiveDay createDiveDayPort(InCreateDiveDay input) {
        return createDiveDayPort.createDiveDay(input);
    }

}