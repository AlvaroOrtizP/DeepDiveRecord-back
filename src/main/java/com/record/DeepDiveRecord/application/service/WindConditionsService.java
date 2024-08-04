package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.WindConditionsUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.common.Pagination;
import com.record.DeepDiveRecord.domain.model.dto.response.windconditions.OutGetData;
import com.record.DeepDiveRecord.domain.model.dto.response.windconditions.OutGetDataList;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WindConditionsService implements WindConditionsUseCase {
    @Autowired
    WindConditionsPort windConditionsPort;

    @Override
    public OutGetDataList getDeepDiveDataByDays(InGetDataWeek input) {
        Page<WindConditionsEntity> outGetDataListsEntity = windConditionsPort.getDeepDiveDataByDays(input);

        OutGetDataList response = new OutGetDataList();
        List<OutGetData> outGetDataLists = new ArrayList<>();

        for (WindConditionsEntity item : outGetDataListsEntity.getContent()) {
            OutGetData outGetData = new OutGetData();
            outGetData.setMonth(item.getId().getMonth());
            outGetData.setDay(item.getId().getDay());
            outGetData.setYear(item.getId().getYear());
            outGetData.setSite(item.getId().getSite());
            outGetData.setTimeOfDay(String.valueOf(item.getId().getTime()));
            outGetData.setWind(item.getWind());
            outGetData.setWindDirection(item.getWindDirection());
            outGetData.setGustsOfWind(item.getGustsOfWind());
            outGetData.setWaveHeight(String.valueOf(item.getWaveHeight()));
            outGetData.setWavePeriod(item.getWavePeriod());
            outGetData.setEarthTemperature(item.getEarthTemperature());
            outGetData.setWaterTtermperature(String.valueOf(item.getWaterTemperature()));
            outGetData.setF1(item.getCodeCondition());
            outGetData.setDescripcion1(item.getConditionDescription());
            outGetDataLists.add(outGetData);
        }


        response.setOutGetDataList(outGetDataLists);
        Pagination pagination = new Pagination();
        pagination.setTotalPages(outGetDataListsEntity.getTotalPages());
        pagination.setTotalElements((int) outGetDataListsEntity.getTotalElements());
        pagination.setSize(outGetDataListsEntity.getSize());
        pagination.setNumber(outGetDataListsEntity.getNumber());
        pagination.setNumberOfElements(outGetDataListsEntity.getNumberOfElements());
        pagination.setIsLast(outGetDataListsEntity.isLast());
        pagination.setIsFirst(outGetDataListsEntity.isFirst());
        pagination.setIsEmpty(outGetDataListsEntity.isEmpty());
        response.setPagination(pagination);

        return response;
    }
}
