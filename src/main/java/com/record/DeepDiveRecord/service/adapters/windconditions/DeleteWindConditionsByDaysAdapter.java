package com.record.DeepDiveRecord.service.adapters.windconditions;

import com.record.DeepDiveRecord.core.windconditions.domain.deletewindcondition.InDataDeleteWC;
import com.record.DeepDiveRecord.core.windconditions.domain.deletewindcondition.OutDeteleWindConditions;
import com.record.DeepDiveRecord.core.windconditions.usecase.deletewindcondition.DeleteWindConditionsUseCase;
import com.record.DeepDiveRecord.entity.WindConditionsId;
import com.record.DeepDiveRecord.mapper.WindConditionsMapper;
import com.record.DeepDiveRecord.repository.windconditions.WindConditionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteWindConditionsByDaysAdapter implements DeleteWindConditionsUseCase {
    @Autowired
    WindConditionsRepository windConditionsRepository;

    @Override
    public OutDeteleWindConditions deleteWindCondition(InDataDeleteWC in) {
        WindConditionsId id = WindConditionsMapper.mapToWindConditionsIdFromInDataDeleteWC(in);
        try {
            windConditionsRepository.deleteById(id);
            return WindConditionsMapper.mapToOutDeteleWindConditionsFromInDataDeleteWC(in, true, null);
        }catch (Exception e){
            return WindConditionsMapper.mapToOutDeteleWindConditionsFromInDataDeleteWC(in, false, e.getMessage());

        }
    }
}
