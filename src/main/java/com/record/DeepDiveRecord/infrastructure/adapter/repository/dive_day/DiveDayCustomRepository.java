package com.record.DeepDiveRecord.infrastructure.adapter.repository.dive_day;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiveDayCustomRepository {
    Page<DiveDayEntity> findDiveDaysByFilters(String site, Pageable pageable);
}
