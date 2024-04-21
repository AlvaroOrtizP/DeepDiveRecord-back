package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.api.domain.InDeepDiveLogger;
import com.record.DeepDiveRecord.core.windconditions.domain.savedata.OutForecast;
import com.record.DeepDiveRecord.mapper.DeepDiveLoggerMapper;
import com.record.DeepDiveRecord.mapper.impl.DeepDiveLoggerMapperImpl;
import com.record.DeepDiveRecord.service.windconditions.usecase.SaveDeepDiveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/windwu")
public class DeepDiveController {

    @Autowired
    SaveDeepDiveDataService webWeatherScraperServive;

    DeepDiveLoggerMapper deepDiveLoggerMapper;

    public DeepDiveController(){
        deepDiveLoggerMapper = new DeepDiveLoggerMapperImpl();
    }

    @PostMapping("/runPythonScript")
    public ResponseEntity<?> runPythonScript(@RequestBody InDeepDiveLogger deepDiveLogger) {

        OutForecast res =  webWeatherScraperServive.runPythonScript(deepDiveLoggerMapper.fromDomainToCore(deepDiveLogger));
        if (res.isOk()){
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(res.getErros(), HttpStatus.BAD_REQUEST);
    }
    
}