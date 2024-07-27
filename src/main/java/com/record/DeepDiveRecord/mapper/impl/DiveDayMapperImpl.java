package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.DiveDayResponse;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import org.springframework.stereotype.Component;

@Component
public class DiveDayMapperImpl implements DiveDayMapper {
    @Override
    public DiveDayResponse responseFromEntity(DiveDayEntity input) {
        DiveDayResponse res = new DiveDayResponse();
        res.setDiveDayId(input.getDiveDayId());
        res.setDay(Integer.valueOf(input.getDay()));
        res.setMonth(Integer.valueOf(input.getMonth()));
        res.setYear(Integer.valueOf(input.getYear()));
        res.setBeginning(input.getBeginning());
        res.setEnd(input.getEnd());
        res.setSite(input.getSite());
        res.setValoracion(input.getAssessment());
        res.setNotes(input.getNotes());
        return res;
    }
}
