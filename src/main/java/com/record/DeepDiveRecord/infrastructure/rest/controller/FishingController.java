package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.FishingUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/fishing")//http://localhost:8080/fishing
@RestController
public class FishingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingController.class);
    @Autowired
    FishingUseCase fishingUseCase;
    @PostMapping()
    public ResponseEntity<Integer> createFishing(@RequestBody InCreateFishing inCreateFishing) {
        try {

            Integer id = fishingUseCase.createFishing(inCreateFishing);
            LOGGER.info("Finaliza el metodo de crear nuevo fishing con id {}", id);

            return ResponseEntity.status(HttpStatus.CREATED).body(inCreateFishing.getIdDiveDay());
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de crear nuevo fishing con error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}