package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.UtilityMapper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiveDayMapperImpl implements DiveDayMapper {
    @Autowired
    private UtilityMapper utilityMapper;
    @Override
    public DiveDayEntity entityFromResponse(InCreateDailyDiving input) {
        DiveDayEntity diveDayEntity = new DiveDayEntity();
        diveDayEntity.setDay(input.getDay());
        diveDayEntity.setMonth(input.getMonth());
        diveDayEntity.setYear(input.getYear());
        diveDayEntity.setBeginning(input.getBeginning());
        diveDayEntity.setEnd(input.getEnd());
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
        res.setAssessment(input.getAssessment());
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

    @Override
    public DiveDayEntity mapRowToDiveDayEntity(Row row) {
        DiveDayEntity entity = new DiveDayEntity();
        entity.setDiveDayId(utilityMapper.getCellValueAsInteger(row.getCell(0)));
        entity.setDay(utilityMapper.getCellValueAsString(row.getCell(1)));
        entity.setBeginning(utilityMapper.getCellValueAsString(row.getCell(2)));
        entity.setEnd(utilityMapper.getCellValueAsString(row.getCell(3)));
        entity.setSite(utilityMapper.getCellValueAsString(row.getCell(4)));
        entity.setNotes(utilityMapper.getCellValueAsString(row.getCell(5)));
        entity.setYear(utilityMapper.getCellValueAsString(row.getCell(6)));
        entity.setMonth(utilityMapper.getCellValueAsString(row.getCell(7)));
        entity.setAssessment(utilityMapper.getCellValueAsInteger(row.getCell(8)));
        GeographicalLocationEntity geogra = new GeographicalLocationEntity();
        geogra.setId(utilityMapper.getCellValueAsInteger(row.getCell(9)));
        entity.setGeographicalLocation(geogra);
        entity.setJellyfish(utilityMapper.getCellValueAsInteger(row.getCell(10)));

        entity.setVisibility(utilityMapper.getCellValueAsInteger(row.getCell(11)));
        entity.setSeaBackground(utilityMapper.getCellValueAsInteger(row.getCell(12)));
        entity.setFishGrass(utilityMapper.getCellValueAsInteger(row.getCell(13)));
        entity.setPresencePlastic(utilityMapper.getCellValueAsInteger(row.getCell(14)));
        return entity;
    }
}
