package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.core.model.windconditions.*;
import com.record.DeepDiveRecord.core.ports.windconditions.CreateWindConditionsPort;
import com.record.DeepDiveRecord.core.ports.windconditions.DeleteWindConditionsPort;
import com.record.DeepDiveRecord.core.ports.windconditions.GetWindConditionsByDaysPort;
import com.record.DeepDiveRecord.core.usecase.windconditions.WindConditionsUseCase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class WindConditionsService implements WindConditionsUseCase {

    DeleteWindConditionsPort deleteWindConditionsPort;
    GetWindConditionsByDaysPort getWindConditionsByDaysPort;
    CreateWindConditionsPort createWindConditionsPort;

    public WindConditionsService( DeleteWindConditionsPort deleteWindConditionsPort, GetWindConditionsByDaysPort getWindConditionsByDaysPort,  CreateWindConditionsPort createWindConditionsPort){
        this.deleteWindConditionsPort = deleteWindConditionsPort;
        this.getWindConditionsByDaysPort = getWindConditionsByDaysPort;
        this.createWindConditionsPort = createWindConditionsPort;
    }
    @Override
    public Page<OutDailyStatistics> getDeepDiveDataByDaysPort(InDataZoneRangeWC in, int page, int size) {
        return getWindConditionsByDaysPort.getDeepDiveDataByDays(in, page, size);
    }

    @Override
    public OutDeteleWindConditions deleteWindConditionPort(InDataDeleteWC in) {
        return deleteWindConditionsPort.deleteWindCondition(in);
    }

    @Override
    public OutForecast createWindCondition(InForecast deepDiveLogger) {

        //Se desactiva
        //return createWindConditionsPort.runPythonScript(deepDiveLogger);
        return new OutForecast();
    }
}
