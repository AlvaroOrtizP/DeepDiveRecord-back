package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.WindConditionsUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/windwu")
public class WindConditionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsController.class);
    @Autowired
    WindConditionsUseCase windConditionsUseCase;

    @PostMapping("/getDataWeek")
    public ResponseEntity<OutGetDataList> getDataWeek(@RequestBody InGetDataWeek inGetDataWeek) {
        LOGGER.info("Comienza el getDataWeek");
        OutGetDataList res = windConditionsUseCase.getDeepDiveDataByDays(inGetDataWeek);


        LOGGER.info("Finaliza el getDataWeek {}", res);

        return new ResponseEntity<>(res, HttpStatus.OK);


    }
}
