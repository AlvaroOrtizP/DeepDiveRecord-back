package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.GeographicalLocationUseCase;
import com.record.DeepDiveRecord.domain.model.dto.response.geographical_location.GeographicalLocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    /**
     * Maneja la solicitud para obtener todas las ubicaciones geográficas.
     * @return ResponseEntity<List<GeographicalLocationResponse>>  - Retorna una lista con todas las ubicaciones geográficas disponibles.
     */
    @GetMapping()
    public ResponseEntity<List<GeographicalLocationResponse>> getAllGeGeographicalLocation() {
        LOGGER.info("Comienza el metodo getAllGeGeographicalLocation ");

        // Llama al caso de uso para obtener todas las ubicaciones geográficas.
        List<GeographicalLocationResponse> res = geographicalLocationUseCase.getAllGeGeographicalLocation();
        LOGGER.info("Finaliza el metodo getAllGeGeographicalLocation con numero de resultados: {}", res.size());

        // Retorna la lista de ubicaciones geográficas.
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}