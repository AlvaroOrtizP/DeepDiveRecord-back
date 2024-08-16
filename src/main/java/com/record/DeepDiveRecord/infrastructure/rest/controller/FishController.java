package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.FishUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;

import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/fish")//http://localhost:8080/fish
@RestController
public class FishController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishController.class);

    @Autowired
    private FishUseCase fishUseCase;

    @GetMapping("")
    public ResponseEntity<List<FishResponse>> getAllFishList() {
        LOGGER.info("Comienza el medoto de obtener la lista de peces");
        List<FishResponse> res = fishUseCase.getAllFishList();
        LOGGER.info("Se obtienen " + res.size());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}