package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.InDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.DeleteDiveDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteDiveDayAdapter implements DeleteDiveDayPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteDiveDayAdapter.class);

    DiveDayMapper diveDayMapper;
    DiveDayRepository repository;

    public DeleteDiveDayAdapter(DiveDayMapper diveDayMapper, DiveDayRepository repository) {
        this.diveDayMapper = diveDayMapper;
        this.repository = repository;
    }

    @Override
    public OutDeleteDiveDay deleteDiveDay(InDeleteDiveDay input) {
        LOGGER.info("Comienza el metodo deleteDiveDay con el input ".concat(input.toString()));
        try {
            LOGGER.info("Se procede a buscar si existe el diveDay");
            Optional<DiveDayEntity> optionalEntity = repository.findById(input.getDiveDayid());

            if (optionalEntity.isPresent()) {
                LOGGER.info("Existe el elemento en la base de datos, se procede a eliminar");
                repository.delete(optionalEntity.get());
            }
            LOGGER.info("Finalizado el delete con exito");

            return new OutDeleteDiveDay(input.getDiveDayid(), new Checker(true, "CORRECT"));
        } catch (Exception e) {
            LOGGER.error("Se produjo un error en el metodo deleteDiveDay ".concat(e.getMessage()));
            return new OutDeleteDiveDay(input.getDiveDayid(), new Checker(false, "ERROR: " + e.getMessage()));
        }
    }
}
