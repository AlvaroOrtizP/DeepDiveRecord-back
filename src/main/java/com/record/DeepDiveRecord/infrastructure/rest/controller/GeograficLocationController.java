package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.GeographicalLocationUseCase;
import com.record.DeepDiveRecord.domain.model.dto.response.GeographicalLocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/GeographicalLocation")
@RestController
public class GeograficLocationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeograficLocationController.class);
    @Autowired
    private GeographicalLocationUseCase geographicalLocationUseCase;


    @GetMapping()
    public List<GeographicalLocationResponse> getAllGeGeographicalLocation() {
        LOGGER.info("Comienza el metodo getAllGeGeographicalLocation ");
        List<GeographicalLocationResponse> res = geographicalLocationUseCase.getAllGeGeographicalLocation();
        LOGGER.info("Finaliza el metodo getAllGeGeographicalLocation con numero de resultados: {}", res.size());
        return res;
    }
}