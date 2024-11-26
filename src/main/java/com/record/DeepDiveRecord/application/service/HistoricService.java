package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.HistoricUseCase;
import com.record.DeepDiveRecord.domain.model.dto.port.historic.CombinedItemDto;
import com.record.DeepDiveRecord.domain.port.HistoricPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class HistoricService implements HistoricUseCase {
    private static final Logger log = LoggerFactory.getLogger(HistoricService.class);
    @Autowired
    private HistoricPort historicPort;
    @Override
    public int securityData() throws Exception {
        CombinedItemDto combinedItemDto = historicPort.read();
        return historicPort.process(combinedItemDto);
    }

    @Override
    public int saveLastSecutiry() throws IOException {
        // Buscar el último archivo .xlsx generado
        File file = historicPort.getLasthistoric();
        if (file == null) {
            log.error("No se encontró ningún archivo para procesar.");
            return 0;
        }

        log.info("Procesando archivo: " + file.getAbsolutePath());
        int res = historicPort.saveHistoric(file);
        return res;
    }
}
