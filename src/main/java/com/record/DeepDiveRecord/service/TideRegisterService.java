package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.core.model.tidetable.InCreateTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.InDeleteTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutCreateTideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.OutDeleteTideRegister;
import com.record.DeepDiveRecord.core.ports.tidetable.CreateTideRegisterPort;
import com.record.DeepDiveRecord.core.ports.tidetable.DeleteTideRegisterPort;
import com.record.DeepDiveRecord.core.usecase.tidetable.TideRegisterUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TideRegisterService implements TideRegisterUseCase {
    CreateTideRegisterPort createTideRegisterPort;
    DeleteTideRegisterPort deleteTideRegisterPort;

    public TideRegisterService(CreateTideRegisterPort createTideRegisterPort,  DeleteTideRegisterPort deleteTideRegisterPort){
        this.createTideRegisterPort = createTideRegisterPort;
        this.deleteTideRegisterPort = deleteTideRegisterPort;

    }

    @Override
    public OutDeleteTideRegister deleteTideRegisterPort(InDeleteTideRegister input) {
        // 0 Añadir filtros

        // 1 Añadir parametros estaticos

        // 2 llamada al port
        return deleteTideRegisterPort.deleteTideRegister(input);
    }

    @Override
    public OutCreateTideRegister createTideRegisterMonthPort(InCreateTideRegister input) {
        // 0 Añadir filtros

        // 1 Añadir parametros estaticos

        // 2 llamada al port
        return createTideRegisterPort.createTideRegisterMonth(input);
    }
}
