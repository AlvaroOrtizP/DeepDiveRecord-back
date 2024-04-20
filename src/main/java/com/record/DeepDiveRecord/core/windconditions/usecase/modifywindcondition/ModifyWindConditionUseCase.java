package com.record.DeepDiveRecord.core.windconditions.usecase.modifywindcondition;

import com.record.DeepDiveRecord.core.windconditions.domain.modifywindcondition.InModifyWindCondition;
import com.record.DeepDiveRecord.core.windconditions.domain.modifywindcondition.OutModifyWindCondition;

import java.util.List;

public interface ModifyWindConditionUseCase {
    List<OutModifyWindCondition> getDeepDiveDataByDays(InModifyWindCondition in);

}
