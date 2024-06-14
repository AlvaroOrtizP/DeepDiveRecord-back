package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutUpdatedDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.UpdateDiveDayPort;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateDiveDayAdapter implements UpdateDiveDayPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDiveDayAdapter.class);

    DiveDayMapper mapper;
    DiveDayRepository repository;

    public UpdateDiveDayAdapter ( DiveDayMapper mapper,  DiveDayRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }
    @Override
    public OutUpdatedDiveDay updatedDiveDay(InUpdatedDiveDay input) {
        LOGGER.info("Comienza el metodo updatedDiveDay con el input ".concat(input.toString()));
        if(Integer.valueOf(input.getDiveDayNew().getBeginning()) > Integer.valueOf(input.getDiveDayNew().getEnd())){
            return new OutUpdatedDiveDay(input.getDiveDayNew(), new Checker(false, "INCORRECT TIME RANGE"));
        }
        try{
            DiveDayEntity diveDayEntity =  mapper.fromUpdateCoreToEntity(input);
            LOGGER.info("Se mappea el input a la entidad ".concat(diveDayEntity.toString()));
            LOGGER.info("Se procede a buscar si existe el diveDay");
            Optional<DiveDayEntity> optionalEntity = repository.findById(diveDayEntity.getDiveDayId());

            if (optionalEntity.isPresent()) {
                LOGGER.info("Existe el elemento en la base de datos, se procede a eliminar");
                repository.save(diveDayEntity);
                return new OutUpdatedDiveDay(input.getDiveDayNew(), new Checker(true, "CORRECT"));
            }else{
                LOGGER.info("No existe el elemento en la base de datos");
                return new OutUpdatedDiveDay(input.getDiveDayNew(), new Checker(false, "NOT FOUND"));
            }

        }catch (Exception e){
            return new OutUpdatedDiveDay(input.getDiveDayNew(), new Checker(false, "ERROR: "+ e.getMessage()));
        }
    }
}
