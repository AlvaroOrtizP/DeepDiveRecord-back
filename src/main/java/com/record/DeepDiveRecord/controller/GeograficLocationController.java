package com.record.DeepDiveRecord.controller;

import com.record.DeepDiveRecord.api.domain.geograficlocation.GeographicalLocationResponse;
import com.record.DeepDiveRecord.service.GeographicalLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/GeographicalLocation")
@RestController
public class GeograficLocationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeograficLocationController.class);

    private GeographicalLocationService geographicalLocationService;

    public GeograficLocationController(GeographicalLocationService geographicalLocationService) {
        this.geographicalLocationService = geographicalLocationService;
    }

    @GetMapping()
    public List<GeographicalLocationResponse> getAllGeGeographicalLocation() {
        LOGGER.info("Comienza el metodo getAllGeGeographicalLocation ");
        List<GeographicalLocationResponse> res = geographicalLocationService.getAllGeGeographicalLocation();
        LOGGER.info("Finaliza el metodo getAllGeGeographicalLocation con numero de resultados: {}", res.size());
        return res;
    }
}
