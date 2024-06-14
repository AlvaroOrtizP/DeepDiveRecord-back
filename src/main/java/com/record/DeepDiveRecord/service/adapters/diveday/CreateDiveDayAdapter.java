package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.CreateDiveDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import com.record.DeepDiveRecord.service.adapters.windconditions.CreateWindConditionsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateDiveDayAdapter implements CreateDiveDayPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateDiveDayAdapter.class);

    DiveDayMapper mapper;
    DiveDayRepository diveDayRepository;

    public CreateDiveDayAdapter ( DiveDayMapper mapper, DiveDayRepository diveDayRepository){{
        this.mapper= mapper;
        this.diveDayRepository = diveDayRepository;
    }

    }
    @Override
    public OutCreateDiveDay createDiveDay(InCreateDiveDay input) {
        LOGGER.info("Comienza el metodo createDiveDay con input: ".concat(input.toString()));
        DiveDayEntity diveDayEntity = mapper.fromCreateCoreToEntity(input);
        LOGGER.info("Se mappea el input a la entidad ".concat(diveDayEntity.toString()));

        try {
            LOGGER.info("Comienza el save de los datos");

            diveDayRepository.save(diveDayEntity);
            LOGGER.info("Finalizado el save de los datos con exito");
            return new OutCreateDiveDay(mapper.fromCoreToOutCore(input).getDiveDay(), new Checker(true, "CORRECT"));

        } catch (Exception e) {
            LOGGER.error("Finalizado el save de los datos con errores, ".concat( e.getMessage()));
            return new OutCreateDiveDay(mapper.fromCoreToOutCore(input).getDiveDay(), new Checker(true, "ERROR "+ e.getMessage()));
        }


    }
}
