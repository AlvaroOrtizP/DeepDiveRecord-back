package com.record.DeepDiveRecord.core.windconditions.usecase.deletewindcondition;


import com.record.DeepDiveRecord.core.windconditions.domain.deletewindcondition.InDataDeleteWC;
import com.record.DeepDiveRecord.core.windconditions.domain.deletewindcondition.OutDeteleWindConditions;

public interface DeleteWindConditionsUseCase {
    OutDeteleWindConditions deleteWindCondition(InDataDeleteWC in);

}
