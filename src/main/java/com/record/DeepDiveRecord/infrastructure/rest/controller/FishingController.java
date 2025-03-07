package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.FishingUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.delete.InDeleteFishing;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.edit.InEditFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.create.FishingDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<FishingDetails> getFishingById(@PathVariable Integer id) {
        try {
            FishingDetails res = fishingUseCase.getFishingById(id);
            LOGGER.info("Finaliza el metodo de obtener el fishing con id {}", id);

            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de obtener el fishing da error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PatchMapping()
    public ResponseEntity<Integer> editFishing(@RequestBody InEditFishing input) {
        try {
            LOGGER.info("Comienza el metodo de editar fishing con id {}", input.getFishingId());
            Integer id = fishingUseCase.editFishing(input);
            LOGGER.info("Finaliza el metodo de editar fishing con id {}", id);

            return ResponseEntity.status(HttpStatus.OK).body(input.getIdDiveDay());
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de editar fishing con error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteFishing(@RequestBody InDeleteFishing input) {
        try {
            LOGGER.info("Comienza el metodo de delete fishing con id {}", input.getFishingId());
           // fishingUseCase.deleteFishingById(input.getFishingId());
            LOGGER.info("Finaliza el metodo de delete fishing con id {}", input.getFishingId());

            return ResponseEntity.status(HttpStatus.OK).body(input.getFishingId());
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de delete fishing con error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}