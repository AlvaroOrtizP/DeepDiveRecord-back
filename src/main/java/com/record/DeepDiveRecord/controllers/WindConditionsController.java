package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.api.domain.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.api.domain.windconditions.OutGetDataList;
import com.record.DeepDiveRecord.core.model.windconditions.InDataZoneRangeWC;
import com.record.DeepDiveRecord.core.model.windconditions.OutDailyStatistics;
import com.record.DeepDiveRecord.core.usecase.windconditions.WindConditionsUseCase;
import com.record.DeepDiveRecord.mapper.PaginationMapper;
import com.record.DeepDiveRecord.mapper.WindConditionsMapper;
import com.record.DeepDiveRecord.service.adapters.diveday.DeleteDiveDayAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/windwu")//TODO cambiar las url ya que solo existen 2 y deberia ser get"" y post ""
public class WindConditionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsController.class);
    WindConditionsUseCase windConditionsUseCase;
    WindConditionsMapper windConditionsMapper;
    PaginationMapper paginationMapper;

    public WindConditionsController(WindConditionsUseCase windConditionsUseCase, WindConditionsMapper windConditionsMapper, PaginationMapper paginationMapper) {
        this.windConditionsUseCase = windConditionsUseCase;
        this.windConditionsMapper = windConditionsMapper;
        this.paginationMapper = paginationMapper;
    }


    //Se usa post ya que los parametros a mandar para identificar el dato son demasiados
    @PostMapping("/getDataWeek")
    public ResponseEntity<OutGetDataList> getDataWeek(@RequestBody InGetDataWeek inGetDataWeek) {

        LOGGER.info("Comienza el getDataWeek");

        InDataZoneRangeWC inDataZoneRangeWC = windConditionsMapper.mapToInDataZoneRangeWCFromInGetDataWeek(inGetDataWeek);


        Page<OutDailyStatistics> res = windConditionsUseCase.getDeepDiveDataByDaysPort(inDataZoneRangeWC, inGetDataWeek.getPage(), inGetDataWeek.getSize());

        OutGetDataList response = new OutGetDataList();
        response.setOutGetDataList(windConditionsMapper.mapOutDailyStatisticsListToOutGetDataList(res.getContent()));
        response.setPagination(paginationMapper.toAPIDomain(res));
        LOGGER.info("Finaliza el getDataWeek");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}