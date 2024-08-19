package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiveDayPort {
    DiveDayEntity findById(Integer id);

    DiveDayEntity save(DiveDayEntity diveDayEntity);

    Page<DiveDayEntity> findByFilters(String zona, Pageable pageable);
}
