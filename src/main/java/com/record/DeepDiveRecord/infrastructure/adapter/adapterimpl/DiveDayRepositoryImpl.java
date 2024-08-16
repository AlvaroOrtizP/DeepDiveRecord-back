package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.dive_day.DiveDayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiveDayRepositoryImpl implements DiveDayPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayRepositoryImpl.class);

    @Autowired
    private DiveDayRepository diveDayRepository;

    @Override
    public DiveDayEntity findById(Integer id) {

        return diveDayRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("DiveDayEntity no encontrado con el ID: {}", id);
                    return new EntityNotFoundException("DiveDayEntity not found with id " + id);
                });
    }

    @Override
    public DiveDayEntity save(DiveDayEntity diveDayEntity) {
        return diveDayRepository.save(diveDayEntity);
    }
}
