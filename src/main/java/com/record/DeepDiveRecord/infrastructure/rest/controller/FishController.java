package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.FishUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fish")
public class FishController {

    @Autowired
    private FishUseCase fishUseCase;

    @PostMapping("/create")
    public void createFish(@RequestBody FishCreateRequest input) {
        fishUseCase.createFish(input);
    }
}