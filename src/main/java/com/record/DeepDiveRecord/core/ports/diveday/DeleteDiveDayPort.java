package com.record.DeepDiveRecord.core.ports.diveday;

import com.record.DeepDiveRecord.core.model.diveday.InDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;

public interface DeleteDiveDayPort {
    OutDeleteDiveDay deleteDiveDay(InDeleteDiveDay input);
}
