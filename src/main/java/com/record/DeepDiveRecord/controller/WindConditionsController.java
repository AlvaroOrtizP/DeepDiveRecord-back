package com.record.DeepDiveRecord.controller;

import com.record.DeepDiveRecord.api.domain.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.api.domain.windconditions.OutGetData;
import com.record.DeepDiveRecord.api.domain.windconditions.OutGetDataList;
import com.record.DeepDiveRecord.api.domain.windconditions.Pagination;
import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.service.WindConditionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/windwu")
public class WindConditionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsController.class);
    private WindConditionsService windConditionsService;

    public WindConditionsController(WindConditionsService windConditionsService){
        this.windConditionsService = windConditionsService;
    }
    @PostMapping("/getDataWeek")
    public ResponseEntity<OutGetDataList> getDataWeek(@RequestBody InGetDataWeek inGetDataWeek) {

        LOGGER.info("Comienza el getDataWeek");

        Page<WindConditionsEntity> outGetDataListsEntity = windConditionsService.getDeepDiveDataByDays(inGetDataWeek);



        OutGetDataList response = new OutGetDataList();
        List<OutGetData> outGetDataLists = new ArrayList<>();

        for(WindConditionsEntity item:  outGetDataListsEntity.getContent()){
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



        LOGGER.info("Finaliza el getDataWeek");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
