package com.record.DeepDiveRecord.core.ports.tidetable;

import com.record.DeepDiveRecord.core.model.tidetable.InDeleteTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutDeleteTideRegister;

public interface DeleteTideRegisterPort {
    OutDeleteTideRegister deleteTideRegister(InDeleteTideRegister input);
}
