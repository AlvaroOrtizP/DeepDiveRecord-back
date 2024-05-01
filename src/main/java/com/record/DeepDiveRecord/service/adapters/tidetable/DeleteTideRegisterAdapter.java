package com.record.DeepDiveRecord.service.adapters.tidetable;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.tidetable.InDeleteTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutDeleteTideRegister;
import com.record.DeepDiveRecord.core.ports.tidetable.DeleteTideRegisterPort;
import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.mapper.TideRegisterMapper;
import com.record.DeepDiveRecord.repository.TideRegisterRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteTideRegisterAdapter implements DeleteTideRegisterPort {
    TideRegisterRepository repository;
    TideRegisterMapper mapper;
    public DeleteTideRegisterAdapter(  TideRegisterRepository repository, TideRegisterMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OutDeleteTideRegister deleteTideRegister(InDeleteTideRegister input) {


        try {
            Optional<TideTableEntity> optionalEntity = repository.findById(mapper.fromDeleteCoreToEntityId(input));

            optionalEntity.ifPresent(tideTableEntity -> repository.delete(tideTableEntity));

            return new OutDeleteTideRegister(input.getDay(), input.getMonth(), input.getYear(), input.getSite(), new Checker(true, "CORRECT"));
        } catch (Exception e) {
            return new OutDeleteTideRegister(input.getDay(), input.getMonth(), input.getYear(), input.getSite(), new Checker(false, "ERROR: " + e.getMessage()));
        }
    }
}
