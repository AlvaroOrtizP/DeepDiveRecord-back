package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.DiveDayMapper;
import org.springframework.stereotype.Component;

@Component
public class DiveDayMapperImpl implements DiveDayMapper {
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

    @Override
    public DiveDayDetailsResponse responseFromEntity(DiveDayEntity input) {
        DiveDayDetailsResponse res = new DiveDayDetailsResponse();
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
    public DiveDayResponse mapToResponse(DiveDayEntity diveDay) {
        DiveDayResponse res = new DiveDayResponse();
        res.setId(diveDay.getDiveDayId());
        res.setDate(diveDay.getDay()+ "/" + diveDay.getMonth()+ "/" + diveDay.getYear());
        res.setSite(diveDay.getGeographicalLocation().getName());
        res.setAssessment(diveDay.getAssessment());
        return res;
    }
}
