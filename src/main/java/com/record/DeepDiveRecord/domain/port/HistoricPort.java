package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.domain.model.dto.port.historic.CombinedItemDto;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


public interface HistoricPort {
    CombinedItemDto read();

    int process(CombinedItemDto input) throws IOException;

    File getLasthistoric();

    int saveHistoric(File file) throws IOException;
}
