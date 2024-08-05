package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.DiveDayUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.diveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.DiveDayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dailyDiving")//http://localhost:8080/dailyDiving
@RestController
public class DiveDayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayController.class);
    @Autowired
    DiveDayUseCase diveDayUseCase;

    @PostMapping()
    public ResponseEntity<Integer> createDiveDay(@RequestBody InCreateDailyDiving inCreateDailyDiving) {
        try {
            LOGGER.info("Comienza el metodo de crear nuevo diveDay con los datos {}", inCreateDailyDiving);
            Integer id = diveDayUseCase.createDiveDay(inCreateDailyDiving);
            LOGGER.info("Finaliza el metodo de crear nuevo diveday con id {}", id);

            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de crear nuevo diveday con error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<DiveDayResponse> getDiveDayById(@PathVariable Integer id) {

        LOGGER.info("Comienza el metodo getDiveDayById con los datos {}", id);
        DiveDayResponse res = diveDayUseCase.findDiveDayById(id);
        LOGGER.info("Se devuelve {}", res);
        return new ResponseEntity<>(res, HttpStatus.OK);


    }

}
