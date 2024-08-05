package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.request.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.WindConditionResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.WindConditionsMapper;
import org.springframework.stereotype.Component;

@Component
public class WindConditionsMapperImpl implements WindConditionsMapper {
    @Override
    public InGetDataWeek fromDiveDayEntity(DiveDayEntity input) {
        InGetDataWeek res = new InGetDataWeek();
        res.setPage(0);
        res.setSize(8);
        res.setSite(input.getSite());
        res.setFromYear(Integer.valueOf(input.getYear()));
        res.setFromMonth(Integer.valueOf(input.getMonth()));
        res.setFromDay(Integer.valueOf(input.getDay()));

        res.setToDay(Integer.valueOf(input.getYear()));
        res.setToMonth(Integer.valueOf(input.getMonth()));
        res.setToYear(Integer.valueOf(input.getYear()));
        return res;
    }

    @Override
    public WindConditionResponse responseFromEntity(WindConditionsEntity input) {
        WindConditionResponse windConditionResponse = new WindConditionResponse();

        windConditionResponse.setYear(input.getId().getYear());
        windConditionResponse.setMonth(input.getId().getMonth());
        windConditionResponse.setDay(input.getId().getDay());
        windConditionResponse.setTimeOfDay(input.getId().getTime());
        windConditionResponse.setSite(input.getId().getSite());
        windConditionResponse.setWind(input.getWind());
        windConditionResponse.setWindDirection(input.getWindDirection().intValue());
        windConditionResponse.setWindDirectionNM("NA");
        windConditionResponse.setGustsOfWind(12);
        windConditionResponse.setWaveHeight(String.valueOf(input.getWaveHeight()));
        windConditionResponse.setWavePeriod(input.getWavePeriod());
        windConditionResponse.setWaveDirection("12");
        windConditionResponse.setWaveDirectionNM("NM");
        windConditionResponse.setEarthTemperature(input.getEarthTemperature());
        windConditionResponse.setWaterTermperature(String.valueOf(input.getWaterTemperature()));
        windConditionResponse.setConditionCode(input.getCodeCondition());
        windConditionResponse.setConditionDescription(input.getConditionDescription());
        return windConditionResponse;
    }
}
