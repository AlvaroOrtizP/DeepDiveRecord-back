package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.InDeepDiveLogger;
import com.record.DeepDiveRecord.core.forecast.domain.savedata.InForecast;
import com.record.DeepDiveRecord.mapper.DeepDiveLoggerMapper;

public class DeepDiveLoggerMapperImpl implements DeepDiveLoggerMapper {

    @Override
    public InForecast fromDomainToCore(InDeepDiveLogger deepDiveLogger) {
        InForecast res = new InForecast();
        res.setLugar(deepDiveLogger.getLugar());
        res.setIdWindwuru(deepDiveLogger.getIdWindwuru());
        res.setIdAemet(deepDiveLogger.getIdAemet());
        return res;
    }
}
