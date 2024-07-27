package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.TideTableResponse;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.entity.TideTableId;
import com.record.DeepDiveRecord.mapper.TideTableMapper;
import org.springframework.stereotype.Component;

@Component
public class TideTableImpl implements TideTableMapper {
    @Override
    public TideTableId entityIdFromDiveDayEntity(DiveDayEntity input) {
        TideTableId tideTableId = new TideTableId();
        tideTableId.setDay(input.getDay());
        tideTableId.setYear(input.getYear());
        tideTableId.setMonth(input.getMonth());
        tideTableId.setSite(input.getSite());
        return tideTableId;
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
}
