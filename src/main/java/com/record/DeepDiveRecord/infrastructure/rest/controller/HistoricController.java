package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.HistoricUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gestor")
@RestController
public class HistoricController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HistoricController.class);
    @Autowired
    private HistoricUseCase gestorUseCase;


    @GetMapping("/")
    public ResponseEntity<?> security() {

        LOGGER.info("Comienza el metodo security");
        int res = 0;
        try {
            res = gestorUseCase.securityData();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
//Numero total de registros: 2265
        return new ResponseEntity<>("Numero total de registros: " + res, HttpStatus.OK);
    }

    @PutMapping("/")
    public  ResponseEntity<?> saveLastSecutiry(){
        LOGGER.info("Comienza el metodo saveLastSecutiry");
        int res = 0;
        try {
            res = gestorUseCase.saveLastSecutiry();
        }catch (Exception e){
            System.out.println("error " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Numero total de registros: " + res, HttpStatus.OK);
    }
}