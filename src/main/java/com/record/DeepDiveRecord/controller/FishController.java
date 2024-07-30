package com.record.DeepDiveRecord.controller;

import com.record.DeepDiveRecord.api.domain.fish.getallfishes.FishResponse;
import com.record.DeepDiveRecord.service.FishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/fish")//http://localhost:8080/fish
@RestController
public class FishController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishController.class);
    FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }
    @GetMapping("")
    public ResponseEntity<List<FishResponse>> getAllFishList() {
        List<FishResponse> res = fishService.getAllFishList();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
