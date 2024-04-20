package com.record.DeepDiveRecord.core.windconditions.usecase.savedata;

import com.record.DeepDiveRecord.core.windconditions.domain.savedata.InForecast;
import com.record.DeepDiveRecord.core.windconditions.domain.savedata.OutForecast;

public interface GetWindConditionsUseCase {
    OutForecast runPythonScript(InForecast deepDiveLogger);
}
