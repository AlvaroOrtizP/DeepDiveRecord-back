package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;

import java.util.Optional;

public interface DiveDayPort {
    Optional<DiveDayEntity> findById(Integer id);

    DiveDayEntity save(DiveDayEntity diveDayEntity);
}
