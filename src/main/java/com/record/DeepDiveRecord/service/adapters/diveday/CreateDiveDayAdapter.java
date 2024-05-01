package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.CreateDiveDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateDiveDayAdapter implements CreateDiveDayPort {

    DiveDayMapper mapper;
    DiveDayRepository diveDayRepository;

    public CreateDiveDayAdapter ( DiveDayMapper mapper, DiveDayRepository diveDayRepository){{
        this.mapper= mapper;
        this.diveDayRepository = diveDayRepository;
    }

    }
    @Override
    public OutCreateDiveDay createDiveDay(InCreateDiveDay input) {

        DiveDayEntity diveDayEntity = mapper.fromCreateCoreToEntity(input);
        try {
            diveDayRepository.save(diveDayEntity);

            return new OutCreateDiveDay(mapper.fromCoreToOutCore(input).getDiveDay(), new Checker(true, "CORRECT"));

        } catch (Exception e) {
            return new OutCreateDiveDay(mapper.fromCoreToOutCore(input).getDiveDay(), new Checker(true, "ERROR "+ e.getMessage()));
        }


    }
}
