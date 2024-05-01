package com.record.DeepDiveRecord.core.ports.diveday;

import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutUpdatedDiveDay;

public interface UpdateDiveDayPort {
    OutUpdatedDiveDay updatedDiveDay(InUpdatedDiveDay input);
}
