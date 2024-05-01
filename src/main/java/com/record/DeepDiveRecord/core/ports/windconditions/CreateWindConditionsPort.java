package com.record.DeepDiveRecord.core.ports.windconditions;

import com.record.DeepDiveRecord.core.model.windconditions.InForecast;
import com.record.DeepDiveRecord.core.model.windconditions.OutForecast;

public interface CreateWindConditionsPort {
    OutForecast runPythonScript(InForecast deepDiveLogger);
}
