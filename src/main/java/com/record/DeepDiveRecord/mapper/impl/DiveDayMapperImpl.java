package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.diveday.creatediveday.InCreateDailyDiving;
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

    @Override
    public DiveDayEntity entityFromResponse(InCreateDailyDiving input) {
        DiveDayEntity diveDayEntity = new DiveDayEntity();
        diveDayEntity.setDay(input.getDay());
        diveDayEntity.setMonth(input.getMonth());
        diveDayEntity.setYear(input.getYear());
        diveDayEntity.setBeginning(input.getBeginning());
        diveDayEntity.setEnd(input.getEnd());

        //idWindwuru
        //Name es el filtro para comprobar que existe en geografia
        diveDayEntity.setNotes(input.getNotes());
        diveDayEntity.setAssessment(input.getAssessment());
        diveDayEntity.setJellyfish(input.getJellyfish());
        diveDayEntity.setVisibility(input.getVisibility());
        diveDayEntity.setSeaBackground(input.getSeaBackground());
        diveDayEntity.setFishGrass(input.getFishGrass());
        diveDayEntity.setPresencePlastic(input.getPresencePlastic());

        return diveDayEntity;
    }
}
