package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.api.domain.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.controller.WindConditionsController;
import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.repository.windconditions.WindConditionsCustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WindConditionsService {
    WindConditionsCustomRepository windConditionsCustomRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsService.class);
    public WindConditionsService(WindConditionsCustomRepository windConditionsCustomRepository) {
        this.windConditionsCustomRepository = windConditionsCustomRepository;
    }

    public Page<WindConditionsEntity> getDeepDiveDataByDays(InGetDataWeek in) {
        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsCustomRepository.customWindConditionsSearch(in.getFromYear(), in.getFromMonth(), in.getFromDay(), in.getToYear(),
                in.getToMonth(), in.getToDay(), in.getSite(), PageRequest.of(in.getPage(), in.getSize()));
        LOGGER.info("El total de resultados es: "+windConditionsEntityPage.getContent().size());
        return windConditionsEntityPage;
    }

}
