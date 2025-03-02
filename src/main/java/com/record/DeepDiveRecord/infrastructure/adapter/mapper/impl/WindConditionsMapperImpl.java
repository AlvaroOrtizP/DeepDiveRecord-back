package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.port.wind_condition.FindDeepDiveDataByDays;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.WindConditionResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetData;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataMedia;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsId;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.UtilityMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.WindConditionsMapper;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WindConditionsMapperImpl implements WindConditionsMapper {
    private static final Logger log = LoggerFactory.getLogger(WindConditionsMapperImpl.class);

    @Autowired
    private UtilityMapper utilityMapper;

    @Override
    public WindConditionResponse responseFromEntity(WindConditionsEntity input) {
        WindConditionResponse windConditionResponse = new WindConditionResponse();

        windConditionResponse.setYear(input.getId().getYear());
        windConditionResponse.setMonth(input.getMonth());
        windConditionResponse.setDayOfYear(input.getId().getDayOfYear());
        windConditionResponse.setDay(input.getDay());
        windConditionResponse.setTimeOfDay(input.getId().getTime());
        windConditionResponse.setSite(input.getId().getSite());
        windConditionResponse.setWind(input.getWind());
        if (input.getWindDirection() != null)
            windConditionResponse.setWindDirection(input.getWindDirection().intValue());

        windConditionResponse.setWindDirectionNM("NA");
        windConditionResponse.setGustsOfWind(12);

        windConditionResponse.setWaveHeight(String.valueOf(input.getWaveHeight()));
        windConditionResponse.setWavePeriod(input.getWavePeriod());
        windConditionResponse.setWaveDirection("12");
        windConditionResponse.setWaveDirectionNM("NM");
        windConditionResponse.setEarthTemperature(input.getEarthTemperature());
        windConditionResponse.setWaterTemperature(String.valueOf(input.getWaterTemperature()));
        windConditionResponse.setConditionCode(input.getCodeCondition());
        windConditionResponse.setConditionDescription(input.getConditionDescription());
        return windConditionResponse;
    }

    @Override
    public FindDeepDiveDataByDays fromDiveDayEntityToDtoFindDeepData(DiveDayEntity input) {
        FindDeepDiveDataByDays res = new FindDeepDiveDataByDays();
        res.setPage(0);
        res.setSize(13);
        res.setSite(input.getSite());
        res.setFromYear(Integer.valueOf(input.getYear()));
        res.setFromMonth(Integer.valueOf(input.getMonth()));
        res.setFromDay(Integer.valueOf(input.getDay()));

        res.setToDay(Integer.valueOf(input.getDay()));
        res.setToMonth(Integer.valueOf(input.getMonth()));
        res.setToYear(Integer.valueOf(input.getYear()));
        return res;
    }

    @Override
    public FindDeepDiveDataByDays fromInGetDataWeekToDtoFindDeepData(InGetDataWeek input) {
        FindDeepDiveDataByDays res = new FindDeepDiveDataByDays();
        res.setPage(input.getPage());
        res.setSize(input.getSize());
        res.setSite(input.getSite());
        res.setFromYear(input.getFromYear());
        res.setFromMonth(input.getFromMonth());
        res.setFromDay(input.getFromDay());

        res.setToDay(input.getToDay());
        res.setToMonth(input.getToMonth());
        res.setToYear(input.getToYear());
        return res;
    }

    @Override
    public OutGetData getOutGetData(WindConditionsEntity item) {
        OutGetData outGetData = new OutGetData();
        outGetData.setMonth(item.getMonth());
        outGetData.setDayOfYear(item.getId().getDayOfYear());
        outGetData.setDay(item.getDay());
        outGetData.setYear(item.getId().getYear());
        outGetData.setSite(item.getId().getSite());
        outGetData.setTimeOfDay(String.valueOf(item.getId().getTime()));
        outGetData.setWind(item.getWind());
        outGetData.setWindDirection(item.getWindDirection());
        outGetData.setGustsOfWind(item.getGustsOfWind());
        outGetData.setWaveHeight(item.getWaveHeight());
        outGetData.setWavePeriod(item.getWavePeriod());
        outGetData.setWaveDirection(item.getWaveDirection());
        outGetData.setEarthTemperature(item.getEarthTemperature());
        if (null == item.getWaterTemperature()) {
            outGetData.setWaterTemperature("NA");
        } else {
            outGetData.setWaterTemperature(String.valueOf(item.getWaterTemperature()));
        }
        outGetData.setWaveDirection(item.getWaveDirection());
        outGetData.setF1(item.getCodeCondition());
        outGetData.setDescription1(item.getConditionDescription());
        return outGetData;
    }

    @Override
    public OutGetDataMedia getOutGetDataMedia(WindConditionsEntity item) {
        OutGetDataMedia res = new OutGetDataMedia();
        res.setCategory("malo"); // TODO destacada malo
        res.setTimeOfDay(item.getId().getTime()<14 ? 1 : 2);
        res.setMonth(item.getMonth());
        res.setDay(item.getDay());
        res.setDayOfYear(item.getId().getDayOfYear());
        res.setYear(item.getId().getYear());
        res.setSite(item.getId().getSite());
        res.setMinWinter(item.getWind());
        res.setMaxWinter(item.getWind());
        res.setWindDirection(item.getWindDirection());
        res.setMinGustsOfWind(item.getGustsOfWind());
        res.setMaxGustsOfWind(item.getGustsOfWind());
        res.setMinWaveHeight(item.getWavePeriod());
        res.setMaxWaveHeight(item.getWaveHeight());
        res.setMinWavePeriod(item.getWavePeriod());
        res.setMaxWavePeriod(item.getWavePeriod());
        res.setMinEarthTemperature(item.getEarthTemperature());
        res.setMaxEarthTemperature(item.getEarthTemperature());

        if (null == item.getWaterTemperature()) {
            res.setMinWaterTemperature("NA");
            res.setMaxWaterTemperature("NA");
        } else {
            res.setMinWaterTemperature(String.valueOf(item.getWaterTemperature()));
            res.setMaxWaterTemperature(String.valueOf(item.getWaterTemperature()));
        }

        res.setF(item.getCodeCondition());
        res.setDescription(item.getConditionDescription());


        return res;
    }

    @Override
    public WindConditionsEntity mapRowToWindConditionsEntity(Row row) {
        WindConditionsEntity entity = new WindConditionsEntity();

        try {
            WindConditionsId windConditionsId = new WindConditionsId();
            // Asignar valores desde las celdas de la fila a los campos de la entidad
            windConditionsId.setYear(utilityMapper.getCellValueAsInteger(row.getCell(0)));
            windConditionsId.setDayOfYear(utilityMapper.getCellValueAsInteger(row.getCell(1)));
            windConditionsId.setTime(utilityMapper.getCellValueAsInteger(row.getCell(2)));
            windConditionsId.setSite(utilityMapper.getCellValueAsString(row.getCell(3)));

            entity.setId(windConditionsId);

            entity.setMonth(utilityMapper.getCellValueAsInteger(row.getCell(4)));
            entity.setDay(utilityMapper.getCellValueAsInteger(row.getCell(5)));
            entity.setWind(utilityMapper.getCellValueAsInteger(row.getCell(6)));
            if(row.getCell(7)!=null)
                entity.setWindDirection(BigDecimal.valueOf(utilityMapper.getCellValueAsInteger(row.getCell(7))));

            entity.setWindDirectionNm(utilityMapper.getCellValueAsString(row.getCell(8)));
            if(row.getCell(9)!=null)
                entity.setGustsOfWind(utilityMapper.getCellValueAsInteger(row.getCell(9)));
            entity.setWaveHeight(utilityMapper.getCellValueAsDouble(row.getCell(10)));
            entity.setWavePeriod(utilityMapper.getCellValueAsInteger(row.getCell(11)));

            entity.setWaveDirection(utilityMapper.getCellValueAsInteger(row.getCell(12)));
            entity.setWaveDirectionNm(utilityMapper.getCellValueAsString(row.getCell(13)));
            entity.setEarthTemperature(utilityMapper.getCellValueAsInteger(row.getCell(14)));
            entity.setWaterTemperature(utilityMapper.getCellValueAsInteger(row.getCell(15)));
            entity.setCodeCondition(utilityMapper.getCellValueAsInteger(row.getCell(16)));
            entity.setConditionDescription(utilityMapper.getCellValueAsString(row.getCell(17)));

        } catch (Exception e) {
            log.error("Error al mapear fila " + row.getRowNum() + " a WindConditionsEntity", e);
        }

        return entity;
    }


}
