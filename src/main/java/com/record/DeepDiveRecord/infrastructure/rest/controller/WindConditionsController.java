package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.WindConditionsUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/windwu")
public class WindConditionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsController.class);
    @Autowired
    WindConditionsUseCase windConditionsUseCase;
    /**
     * Maneja la solicitud para obtener datos semanales sobre las condiciones del viento.
     * @param inGetDataWeek - Objeto que contiene los parámetros necesarios para obtener los datos semanales.
     * @return ResponseEntity<OutGetDataList> - Retorna una lista de datos sobre las condiciones del viento con el estado HTTP correspondiente.
     */
    @PostMapping("/getDataWeek")
    public ResponseEntity<OutGetDataList> getDataWeek(@RequestBody InGetDataWeek inGetDataWeek) {
        LOGGER.info("Comienza el getDataWeek");
        // Llama al caso de uso para obtener los da
        // tos de buceo profundo para la semana según los días especificados.
        OutGetDataList res = windConditionsUseCase.getDeepDiveDataByDays(inGetDataWeek);

        LOGGER.info("Finaliza el getDataWeek {}", res);

        // Retorna una respuesta HTTP 200 (OK) con los datos obtenidos.
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
