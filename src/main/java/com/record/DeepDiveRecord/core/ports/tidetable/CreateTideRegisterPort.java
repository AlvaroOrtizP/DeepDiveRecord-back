package com.record.DeepDiveRecord.core.ports.tidetable;

import com.record.DeepDiveRecord.core.model.tidetable.InCreateTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutCreateTideRegister;

public interface CreateTideRegisterPort {
    OutCreateTideRegister createTideRegisterMonth(InCreateTideRegister input);
}
