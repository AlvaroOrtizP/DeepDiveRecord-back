package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.DiveDayUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<DiveDayDetailsResponse> getDiveDayById(@PathVariable Integer id) {
        LOGGER.info("Comienza el metodo getDiveDayById con los datos {}", id);
        try {
            DiveDayDetailsResponse res = diveDayUseCase.findDiveDayById(id);
            LOGGER.info("Se devuelve {}", res);
            return ResponseEntity.ok(res);
        } catch (EntityNotFoundException e) {
            LOGGER.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DiveDayResponse>> getDiveDays(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String zona,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "fecha") String sortBy) {

        try {
            LOGGER.info("Comienza el metodo getDiveDays con los datos {}", zona);
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
            return ResponseEntity.ok(diveDayUseCase.findByFilters(zona, pageable));
        } catch (EntityNotFoundException e) {
            LOGGER.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //TODO modificar los Exception para filtrar por Excepciones mas concretas
}
