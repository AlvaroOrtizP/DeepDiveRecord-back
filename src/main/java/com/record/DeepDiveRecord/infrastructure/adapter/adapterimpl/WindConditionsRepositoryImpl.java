package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.dto.port.wind_condition.FindDeepDiveDataByDays;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.wind_conditions.WindConditionsCustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class WindConditionsRepositoryImpl implements WindConditionsPort {
    @Autowired
    WindConditionsCustomRepository windConditionsCustomRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsRepositoryImpl.class);

    @Override
    public Page<WindConditionsEntity> getDeepDiveDataByDays(FindDeepDiveDataByDays in) {
        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsCustomRepository.customWindConditionsSearch(in.getFromYear(), in.getFromMonth(), in.getFromDay(), in.getToYear(),
                in.getToMonth(), in.getToDay(), in.getSite(), PageRequest.of(in.getPage(), in.getSize()));
        LOGGER.info("El total de resultados es: "+windConditionsEntityPage.getContent().size());
        return windConditionsEntityPage;
    }
}
