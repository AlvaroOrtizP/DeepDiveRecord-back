package com.record.DeepDiveRecord.infrastructure.rest.controller;

import com.record.DeepDiveRecord.application.usecase.DiveDayUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dailyDiving")//http://localhost:8080/dailyDiving
@RestController
public class DiveDayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayController.class);
    @Autowired
    DiveDayUseCase diveDayUseCase;

    /**
     * Maneja la creación de un nuevo día de buceo.
     * @param inCreateDailyDiving - Objeto que contiene los datos necesarios para crear un nuevo día de buceo.
     * @return ResponseEntity<Integer> - Retorna el ID del nuevo día de buceo con el estado HTTP correspondiente.
     */
    @PostMapping()
    public ResponseEntity<Integer> createDiveDay(@RequestBody InCreateDailyDiving inCreateDailyDiving) {
        try {
            LOGGER.info("Comienza el metodo de crear nuevo diveDay con los datos {}", inCreateDailyDiving);
            // Llama al caso de uso para crear un nuevo día de buceo y obtiene su ID.
            Integer id = diveDayUseCase.createDiveDay(inCreateDailyDiving);
            LOGGER.info("Finaliza el metodo de crear nuevo diveday con id {}", id);
            // Retorna una respuesta HTTP 201 (CREATED) con el ID del nuevo día de buceo.
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            LOGGER.error("Finaliza el metodo de crear nuevo diveday con error {}", e.getMessage());
            // Retorna una respuesta HTTP 404 (NOT FOUND) en caso de error.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Maneja la búsqueda de un día de buceo por su ID.
     * @param id - ID del día de buceo a buscar.
     * @return ResponseEntity<DiveDayDetailsResponse> - Retorna los detalles del día de buceo si se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiveDayDetailsResponse> getDiveDayById(@PathVariable Integer id) {

        LOGGER.info("Comienza el metodo getDiveDayById con los datos {}", id);
        // Llama al caso de uso para encontrar el día de buceo por ID.
        DiveDayDetailsResponse res = diveDayUseCase.findDiveDayById(id);
        LOGGER.info("Se devuelve {}", res);
        // Si se encuentra el día de buceo, retorna una respuesta HTTP 200 (OK) con los detalles.
        if(res!=null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        // Si no se encuentra, retorna una respuesta HTTP 404 (NOT FOUND).
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la búsqueda paginada y filtrada de días de buceo.
     * @param page - Número de página solicitada (por defecto 0).
     * @param size - Tamaño de la página (por defecto 10).
     * @param zona - Filtro opcional por zona de buceo.
     * @param sortDirection - Dirección del orden (ascendente o descendente).
     * @param sortBy - Campo por el cual se ordenará (por defecto, 'fecha').
     * @return ResponseEntity<Page<DiveDayResponse>> - Retorna una página con la lista de días de buceo que coinciden con los filtros.
     */
    @GetMapping("/list")
    public ResponseEntity<Page<DiveDayResponse>> getFishingDays(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String zona,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "fecha") String sortBy) {

        LOGGER.info("Comienza el metodo getFishingDays con los datos {}", zona);
        // Crea un objeto Pageable que define la paginación y la ordenación.
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);

        // Llama al caso de uso para obtener una lista paginada de días de buceo con los filtros aplicados.
        Page<DiveDayResponse> res = diveDayUseCase.findByFilters(zona, pageable);

        // Retorna una respuesta HTTP 200 (OK) con la página de resultados.
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


}
