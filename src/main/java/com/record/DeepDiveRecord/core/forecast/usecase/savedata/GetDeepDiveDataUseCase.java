package com.record.DeepDiveRecord.core.forecast.usecase.savedata;

import com.record.DeepDiveRecord.core.forecast.domain.savedata.InForecast;
import com.record.DeepDiveRecord.core.forecast.domain.savedata.OutForecast;

public interface GetDeepDiveDataUseCase {
    OutForecast runPythonScript(InForecast deepDiveLogger);
}
