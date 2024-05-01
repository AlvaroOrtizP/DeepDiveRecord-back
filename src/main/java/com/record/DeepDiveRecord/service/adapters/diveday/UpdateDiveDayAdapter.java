package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutUpdatedDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.UpdateDiveDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateDiveDayAdapter implements UpdateDiveDayPort {
    DiveDayMapper mapper;
    DiveDayRepository diveDayRepository;

    public UpdateDiveDayAdapter ( DiveDayMapper mapper,  DiveDayRepository diveDayRepository){
        this.mapper = mapper;
        this.diveDayRepository = diveDayRepository;
    }
    @Override
    public OutUpdatedDiveDay updatedDiveDay(InUpdatedDiveDay input) {
        try{
            DiveDayEntity diveDayEntity =  mapper.fromUpdateCoreToEntity(input);
            diveDayRepository.save(diveDayEntity);
            return new OutUpdatedDiveDay(input.getDiveDayNew(), new Checker(true, "CORRECT"));
        }catch (Exception e){
            return new OutUpdatedDiveDay(input.getDiveDayNew(), new Checker(false, "ERROR: "+ e.getMessage()));
        }
    }
}
