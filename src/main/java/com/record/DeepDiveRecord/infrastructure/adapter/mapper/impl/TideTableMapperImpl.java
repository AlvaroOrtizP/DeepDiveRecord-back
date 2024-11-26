package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.port.tide_table.FindTideTable;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.TideTableResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableId;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.TideTableMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.UtilityMapper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TideTableMapperImpl implements TideTableMapper {
    @Autowired
    private UtilityMapper utilityMapper;
    @Override
    public FindTideTable dtoPortFromDiveDayEntity(DiveDayEntity input) {
        FindTideTable findTideTable = new FindTideTable();
        findTideTable.setDay(input.getDay());
        findTideTable.setYear(input.getYear());
        findTideTable.setMonth(input.getMonth());
        findTideTable.setSite(input.getSite());
        return findTideTable;
    }

    @Override
    public TideTableResponse responseFromEntity(TideTableEntity input) {
        TideTableResponse tideTableResponse = new TideTableResponse();
        tideTableResponse.setDay(Integer.valueOf(input.getId().getDay()));
        tideTableResponse.setMonth(Integer.valueOf(input.getId().getMonth()));
        tideTableResponse.setYear(Integer.valueOf(input.getId().getYear()));
        tideTableResponse.setSite(Integer.valueOf(input.getId().getSite()));
        tideTableResponse.setMoonPhase(input.getMoonPhase());
        tideTableResponse.setCoefficient0H(input.getCoefficient0H());
        tideTableResponse.setCoefficient12H(input.getCoefficient12H());
        tideTableResponse.setMorningHighTideTime(input.getMorningHighTideTime());
        tideTableResponse.setMorningHighTideHeight(String.valueOf(input.getMorningHighTideHeight()));
        tideTableResponse.setAfternoonHighTideTime(input.getAfternoonHighTideTime());
        tideTableResponse.setAfternoonHighTideHeight(String.valueOf(input.getAfternoonHighTideHeight()));
        tideTableResponse.setMorningLowTideTime(input.getMorningLowTideTime());
        tideTableResponse.setMorningLowTideHeight(String.valueOf(input.getMorningLowTideHeight()));
        tideTableResponse.setAfternoonLowTideTime(input.getAfternoonLowTideTime());
        tideTableResponse.setAfternoonLowTideHeight(String.valueOf(input.getAfternoonLowTideHeight()));
        return tideTableResponse;
    }

    @Override
    public TideTableId entityIdFromDtoPort(FindTideTable input) {
        TideTableId tideTableId = new TideTableId();
        tideTableId.setDay(input.getDay());
        tideTableId.setYear(input.getYear());
        tideTableId.setMonth(input.getMonth());
        tideTableId.setSite(input.getSite());
        return tideTableId;
    }

    @Override
    public TideTableEntity mapRowToTideTableEntity(Row row) {
        TideTableEntity entity = new TideTableEntity();
        TideTableId id = new TideTableId();
        id.setDay(utilityMapper.getCellValueAsString(row.getCell(0)));
        id.setMonth(utilityMapper.getCellValueAsString(row.getCell(1)));
        id.setYear(utilityMapper.getCellValueAsString(row.getCell(2)));
        id.setSite(utilityMapper.getCellValueAsString(row.getCell(3)));
        entity.setId(id);
        entity.setMoonPhase(utilityMapper.getCellValueAsInteger(row.getCell(4)));
        entity.setCoefficient0H(utilityMapper.getCellValueAsInteger(row.getCell(5)));
        entity.setCoefficient12H(utilityMapper.getCellValueAsInteger(row.getCell(6)));
        entity.setMorningHighTideTime(utilityMapper.getCellValueAsString(row.getCell(7)));
        entity.setMorningHighTideHeight(utilityMapper.getCellValueAsInteger(row.getCell(8)));
        entity.setAfternoonHighTideTime(utilityMapper.getCellValueAsString(row.getCell(9)));
        entity.setAfternoonHighTideHeight(utilityMapper.getCellValueAsInteger(row.getCell(10)));
        entity.setMorningLowTideTime(utilityMapper.getCellValueAsString(row.getCell(11)));
        entity.setMorningLowTideHeight(utilityMapper.getCellValueAsInteger(row.getCell(12)));
        entity.setAfternoonLowTideTime(utilityMapper.getCellValueAsString(row.getCell(13)));
        entity.setAfternoonLowTideHeight(utilityMapper.getCellValueAsInteger(row.getCell(14)));

        return entity;
    }
}
