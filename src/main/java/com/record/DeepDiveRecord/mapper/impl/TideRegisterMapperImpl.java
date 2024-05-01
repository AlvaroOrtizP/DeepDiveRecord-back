package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.core.model.common.TideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.InCreateTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.InDeleteTideRegister;
import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.entity.TideTableId;
import com.record.DeepDiveRecord.mapper.TideRegisterMapper;
import org.springframework.stereotype.Component;

@Component
public class TideRegisterMapperImpl implements TideRegisterMapper {
    @Override
    public TideTableEntity fromCreateCoreToEntity(TideRegister input) {

        TideTableEntity res = new TideTableEntity();
        TideTableId id = new TideTableId();
        id.setDay(input.getDay());
        id.setSite(input.getSite());
        id.setMonth(input.getMonth());
        id.setYear(input.getYear());
        res.setId(id);

        res.setMoonPhase(input.getLunarPhase().getValue());

        res.setMorningHighTideTime(input.getMorningHighTideTime());
        res.setMorningLowTideHeight(input.getMorningLowTideHeight());

        res.setAfternoonHighTideTime(input.getEveningHighTideTime());
        res.setAfternoonHighTideHeight(input.getEveningHighTideHeight());

        res.setMorningLowTideTime(input.getMorningLowTideTime());
        res.setMorningHighTideHeight(input.getMorningHighTideHeight());

        res.setAfternoonLowTideHeight(input.getEveningLowTideHeight());
        res.setAfternoonLowTideTime(input.getEveningLowTideTime());

        return res;
    }

    @Override
    public TideTableId fromDeleteCoreToEntityId(InDeleteTideRegister input) {
        TideTableId res = new TideTableId();
        res.setYear(input.getYear());
        res.setSite(input.getSite());
        res.setMonth(input.getMonth());
        res.setDay(input.getDay());
        return res;
    }
}
