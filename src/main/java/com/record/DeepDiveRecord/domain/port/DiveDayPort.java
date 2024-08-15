package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;

public interface DiveDayPort {
    DiveDayEntity findById(Integer id);

    DiveDayEntity save(DiveDayEntity diveDayEntity);
}
