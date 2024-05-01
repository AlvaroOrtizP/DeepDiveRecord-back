package com.record.DeepDiveRecord.service.adapters.windconditions;

import com.record.DeepDiveRecord.core.model.windconditions.InDataDeleteWC;
import com.record.DeepDiveRecord.core.model.windconditions.OutDeteleWindConditions;
import com.record.DeepDiveRecord.core.ports.windconditions.DeleteWindConditionsPort;
import com.record.DeepDiveRecord.entity.WindConditionsId;
import com.record.DeepDiveRecord.mapper.WindConditionsMapper;
import com.record.DeepDiveRecord.repository.windconditions.WindConditionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteWindConditionsByDaysAdapter implements DeleteWindConditionsPort {
    WindConditionsRepository windConditionsRepository;
    WindConditionsMapper mapper;

    public  DeleteWindConditionsByDaysAdapter(WindConditionsRepository windConditionsRepository,  WindConditionsMapper mapper){
        this.windConditionsRepository = windConditionsRepository;
        this.mapper = mapper;
    }

    @Override
    public OutDeteleWindConditions deleteWindCondition(InDataDeleteWC in) {
        WindConditionsId id = mapper.mapToWindConditionsIdFromInDataDeleteWC(in);
        try {
            windConditionsRepository.deleteById(id);
            return mapper.mapToOutDeteleWindConditionsFromInDataDeleteWC(in, true, null);
        }catch (Exception e){
            return mapper.mapToOutDeteleWindConditionsFromInDataDeleteWC(in, false, e.getMessage());

        }
    }
}
