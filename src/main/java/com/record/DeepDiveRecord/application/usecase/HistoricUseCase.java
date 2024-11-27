package com.record.DeepDiveRecord.application.usecase;

import java.io.File;
import java.io.IOException;

public interface HistoricUseCase {
    int securityData() throws Exception;
    int saveLastSecutiry() throws IOException;

    int saveHistoric(File tempFile) throws IOException;
}
