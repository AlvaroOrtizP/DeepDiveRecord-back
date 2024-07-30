package com.record.DeepDiveRecord.controller;

import com.record.DeepDiveRecord.api.domain.diveday.creatediveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.DiveDayResponse;
import com.record.DeepDiveRecord.service.DiveDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dailyDiving")//http://localhost:8080/dailyDiving
@RestController
public class DiveDayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayController.class);
    DiveDayService diveDayService;

    public DiveDayController(DiveDayService diveDayService) {
        this.diveDayService = diveDayService;
    }

    @PostMapping()
    public ResponseEntity<Integer> createDiveDay(@RequestBody InCreateDailyDiving inCreateDailyDiving) {
        try {
            LOGGER.info("Comienza el metodo de crear nuevo diveDay con los datos {}", inCreateDailyDiving);
            Integer id = diveDayService.createDiveDay(inCreateDailyDiving);
            LOGGER.info("Finaliza el metodo de crear nuevo diveday con id {}", id);

            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de crear nuevo diveday con error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiveDayResponse> getDiveDayById(@PathVariable  Integer id) {
        try {
            LOGGER.info("Comienza el metodo getDiveDayById con los datos {}", id);
            DiveDayResponse res = diveDayService.findDiveDayById(id);
            LOGGER.info("Se devuelve {}", res);
            if (res != null) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de getDiveDayById con error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
