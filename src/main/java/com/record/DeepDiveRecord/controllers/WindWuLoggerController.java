package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.dto.request.WebWeatherScraperRequest;
import com.record.DeepDiveRecord.dto.responses.WebWeatherScraperResponse;
import com.record.DeepDiveRecord.service.WebWeatherScraperServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/windwu")
public class WindWuLoggerController {

    @Autowired
    WebWeatherScraperServive webWeatherScraperServive;


    @PostMapping("/runPythonScript")
    public ResponseEntity<?> runPythonScript(@RequestBody WebWeatherScraperRequest webWeatherScraperDto) {
        WebWeatherScraperResponse res =  webWeatherScraperServive.runPythonScript(webWeatherScraperDto);
        if (res.isOk()){
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(res.getErros(), HttpStatus.BAD_REQUEST);
    }
}