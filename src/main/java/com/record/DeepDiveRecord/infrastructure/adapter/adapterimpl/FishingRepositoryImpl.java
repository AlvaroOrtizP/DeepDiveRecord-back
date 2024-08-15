package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.port.FishingPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.fishing.FishingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FishingRepositoryImpl implements FishingPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingRepositoryImpl.class);


    @Autowired
    FishingRepository fishingRepository;
    @Override
    public Integer save(FishingEntity input) {
        LOGGER.info("Se procede a guardar {} ", input);
        FishingEntity res = fishingRepository.save(input);

        return res.getId();
    }
}
