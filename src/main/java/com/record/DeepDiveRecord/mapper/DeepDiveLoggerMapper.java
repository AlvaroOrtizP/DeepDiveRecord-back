package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.InDeepDiveLogger;
import com.record.DeepDiveRecord.core.model.windconditions.InForecast;
import org.springframework.stereotype.Component;


@Component
public interface DeepDiveLoggerMapper {

    InForecast fromDomainToCore(InDeepDiveLogger deepDiveLogger);
}
