package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.InDeepDiveLogger;
import com.record.DeepDiveRecord.core.forecast.domain.savedata.InForecast;



public interface DeepDiveLoggerMapper {

    InForecast fromDomainToCore(InDeepDiveLogger deepDiveLogger);
}
