package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.dive_day.DiveDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiveDayRepositoryImpl  implements DiveDayPort {
    @Autowired
    private DiveDayRepository diveDayRepository;
    @Override
    public Optional<DiveDayEntity> findById(Integer id) {

        return diveDayRepository.findById(id);
    }

    @Override
    public DiveDayEntity save(DiveDayEntity diveDayEntity) {
        return diveDayRepository.save(diveDayEntity);
    }
}
