package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.InDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.DeleteDiveDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteDiveDayAdapter implements DeleteDiveDayPort {
    DiveDayMapper diveDayMapper;
    DiveDayRepository repository;
    public DeleteDiveDayAdapter(DiveDayMapper diveDayMapper,DiveDayRepository repository ){
        this.diveDayMapper = diveDayMapper;
        this.repository = repository;
    }
    @Override
    public OutDeleteDiveDay deleteDiveDay(InDeleteDiveDay input) {
        try {
            Optional<DiveDayEntity> optionalEntity =  repository.findById(input.getDiveDayid());

            optionalEntity.ifPresent(diveDayEntity -> repository.delete(diveDayEntity));

            return new OutDeleteDiveDay(input.getDiveDayid(), new Checker(true, "CORRECT"));
        }
        catch (Exception e){
            return new OutDeleteDiveDay(input.getDiveDayid(), new Checker(false, "ERROR: "+ e.getMessage()));
        }
    }
}
