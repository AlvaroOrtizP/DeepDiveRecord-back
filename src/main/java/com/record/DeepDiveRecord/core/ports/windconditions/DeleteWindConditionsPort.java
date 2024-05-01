package com.record.DeepDiveRecord.core.ports.windconditions;


import com.record.DeepDiveRecord.core.model.windconditions.InDataDeleteWC;
import com.record.DeepDiveRecord.core.model.windconditions.OutDeteleWindConditions;

public interface DeleteWindConditionsPort {
    OutDeteleWindConditions deleteWindCondition(InDataDeleteWC in);

}
