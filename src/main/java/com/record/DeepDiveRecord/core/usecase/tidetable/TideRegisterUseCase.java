package com.record.DeepDiveRecord.core.usecase.tidetable;

import com.record.DeepDiveRecord.core.model.tidetable.InCreateTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.InDeleteTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutCreateTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutDeleteTideRegister;

public interface TideRegisterUseCase {
    OutDeleteTideRegister deleteTideRegisterPort(InDeleteTideRegister input);
    OutCreateTideRegister createTideRegisterMonthPort(InCreateTideRegister input);
}
