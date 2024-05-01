package com.record.DeepDiveRecord.core.ports.diveday;

import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;

public interface CreateDiveDayPort {
    OutCreateDiveDay createDiveDay (InCreateDiveDay input);
}
