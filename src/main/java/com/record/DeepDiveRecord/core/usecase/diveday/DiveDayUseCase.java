package com.record.DeepDiveRecord.core.usecase.diveday;

import com.record.DeepDiveRecord.core.model.diveday.*;

public interface DiveDayUseCase {
    OutUpdatedDiveDay updatedDiveDayPort(InUpdatedDiveDay input);
    OutDeleteDiveDay deleteDiveDayPort(InDeleteDiveDay input);
    OutCreateDiveDay createDiveDayPort (InCreateDiveDay input);

}
