package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.api.domain.diveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.api.domain.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.usecase.diveday.DiveDayUseCase;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dailyDiving")//http://localhost:8080/dailyDiving
@RestController
public class DiveDayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayController.class);
    DiveDayUseCase diveDayUseCase;
    DiveDayMapper diveDayMapper;

    public DiveDayController(DiveDayMapper diveDayMapper, DiveDayUseCase diveDayUseCase) {
        this.diveDayMapper = diveDayMapper;
        this.diveDayUseCase = diveDayUseCase;
    }

    @PostMapping()
    public ResponseEntity<OutCreateDiveDay> createDailyDiving(@RequestBody InCreateDailyDiving inCreateDailyDiving) {
        LOGGER.info("Comienza createDailyDiving");
        InCreateDiveDay inCreateDiveDay = diveDayMapper.fromRequestToCore(inCreateDailyDiving);

        com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay outCreateDiveDayModel = diveDayUseCase.createDiveDayPort(inCreateDiveDay);
       // OutCreateDiveDay outCreateDiveDay = diveDayMapper.mapFromOutCreateModelToOutCreateApi(outCreateDiveDayModel);

        //if (outCreateDiveDay.getDiveDayId() == null || outCreateDiveDay.getDiveDayId() == 0) {
            LOGGER.info("Finaliza createDailyDiving sin poder crear el registro");
           // LOGGER.info("Error: "+outCreateDiveDay.getMessage());
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(outCreateDiveDay);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OutCreateDiveDay());
       // }
        //LOGGER.info("Finaliza createDailyDiving");
       // return new ResponseEntity<>(outCreateDiveDay, HttpStatus.CREATED);
    }
}
