package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.dto.request.DailyDivingRequest;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.service.DiveDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DiveDayController {
    @Autowired
    DiveDayService diveDayService;

    @PutMapping("/dailyDiving")
    public ResponseEntity<?> createDailyDiving (@RequestBody DailyDivingRequest dailyDivingRequest){
        DiveDayEntity diveDay = diveDayService.createDailyDiving(dailyDivingRequest);
        if(diveDay.getDiveDayId()==null && diveDay.getDiveDayId().equals("")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar el registro");
        }
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    } 
}
