package com.record.DeepDiveRecord.service.adapters.tidetable;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.TideRegister;
import com.record.DeepDiveRecord.core.model.fishing.OutCreateFishDay;
import com.record.DeepDiveRecord.core.model.tidetable.InCreateTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutCreateTideRegister;
import com.record.DeepDiveRecord.core.ports.tidetable.CreateTideRegisterPort;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.mapper.TideRegisterMapper;
import com.record.DeepDiveRecord.repository.FishingRepository;
import com.record.DeepDiveRecord.repository.TideRegisterRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateTideRegisterAdapter implements CreateTideRegisterPort {
    TideRegisterMapper mapper;
    TideRegisterRepository repository;
    public CreateTideRegisterAdapter(TideRegisterMapper mapper, TideRegisterRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }
    @Override
    public OutCreateTideRegister createTideRegisterMonth(InCreateTideRegister input) {
        TideTableEntity entity = null;
        try {
            for (TideRegister item : input.getTideRegisterList()){
                entity = mapper.fromCreateCoreToEntity(item);
                repository.save(entity);
            }
            //TODO cambiar salida para indicar cual fue Ok y cual no
            return new OutCreateTideRegister(input.getTideRegisterList(), new Checker(true, "CORRECT"));

        } catch (Exception e) {
            return new OutCreateTideRegister(input.getTideRegisterList(), new Checker(true, "ERROR " + e.getMessage()));
        }
    }
}
