package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.FishingUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.FishingDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/fishing")//http://localhost:8080/fishing
@RestController
public class FishingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingController.class);
    @Autowired
    FishingUseCase fishingUseCase;

    /**
     * Maneja la solicitud para crear una nueva entrada de pesca.
     * @param inCreateFishing - Objeto que contiene los datos necesarios para crear una nueva entrada de pesca.
     * @return ResponseEntity<Integer> - Retorna el ID de la nueva entrada de pesca con el estado HTTP correspondiente.
     */
    @PostMapping()
    public ResponseEntity<Integer> createFishing(@RequestBody InCreateFishing inCreateFishing) {
        try {
            // Llama al caso de uso para crear una nueva entrada de pesca y obtiene su ID.
            Integer id = fishingUseCase.createFishing(inCreateFishing);
            LOGGER.info("Finaliza el metodo de crear nuevo fishing con id {}", id);

            // Retorna una respuesta HTTP 201 (CREATED) con el ID del nuevo registro de pesca.
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de crear nuevo fishing con error {}", e.getMessage());

            // Retorna una respuesta HTTP 404 (NOT FOUND) en caso de error.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Maneja la solicitud para obtener los detalles de una entrada de pesca por su ID.
     * @param id - ID de la entrada de pesca a buscar.
     * @return ResponseEntity<FishingDetails> - Retorna los detalles de la entrada de pesca si se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FishingDetails> getFishingById(@PathVariable Integer id) {
        try {
            // Llama al caso de uso para obtener los detalles de la pesca por su ID.
            FishingDetails res = fishingUseCase.getFishingById(id);
            LOGGER.info("Finaliza el metodo de obtener el fishing con id {}", id);

            // Retorna una respuesta HTTP 200 (OK) con los detalles de la pesca.
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de obtener el fishing da error {}", e.getMessage());

            // Retorna una respuesta HTTP 404 (NOT FOUND) en caso de error.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}