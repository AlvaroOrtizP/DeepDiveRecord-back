package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.core.model.diveday.*;
import com.record.DeepDiveRecord.core.ports.diveday.CreateDiveDayPort;
import com.record.DeepDiveRecord.core.ports.diveday.DeleteDiveDayPort;
import com.record.DeepDiveRecord.core.ports.diveday.UpdateDiveDayPort;
import com.record.DeepDiveRecord.core.usecase.diveday.DiveDayUseCase;
import org.springframework.stereotype.Service;

@Service
public class DiveDayService implements DiveDayUseCase {
    UpdateDiveDayPort updateDiveDayPort;
    DeleteDiveDayPort deleteDiveDayPort;
    CreateDiveDayPort createDiveDayPort;

    public DiveDayService(UpdateDiveDayPort updateDiveDayPort,
                          DeleteDiveDayPort deleteDiveDayPort,
                          CreateDiveDayPort createDiveDayPort) {
        this.updateDiveDayPort = updateDiveDayPort;
        this.deleteDiveDayPort = deleteDiveDayPort;
        this.createDiveDayPort = createDiveDayPort;
    }

    /**
     * Metodo para actualizar un registro de dia de buceo
     * TODO
     * @param input
     * @return
     */
    @Override
    public OutUpdatedDiveDay updatedDiveDayPort(InUpdatedDiveDay input) {
        // 0 Añadir filtros

        // 1 Añadir parametros estaticos

        // 2 llamada al port
        return updateDiveDayPort.updatedDiveDay(input);
    }

    /**
     * TODO
     * Metodo para eliminar un registro
     * @param input
     * @return
     */
    @Override
    public OutDeleteDiveDay deleteDiveDayPort(InDeleteDiveDay input) {
        // 0 Añadir filtros

        // 1 Añadir parametros estaticos

        // 2 llamada al port

        return deleteDiveDayPort.deleteDiveDay(input);
    }

    /**
     * Metodo para crear un registro de DiveDay (Dia de buceo)
     * @param input Datos del dia de buceo y la lista de pesca
     * @return registro con DiveDay creado o no
     */
    @Override
    public OutCreateDiveDay createDiveDayPort(InCreateDiveDay input) {
        // 0 Añadir filtros

        // 1 Añadir parametros estaticos

        // 2 llamada al port
        return createDiveDayPort.createDiveDay(input);
    }

}