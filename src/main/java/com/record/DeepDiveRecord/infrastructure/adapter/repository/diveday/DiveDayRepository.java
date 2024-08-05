package com.record.DeepDiveRecord.infrastructure.adapter.repository.diveday;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiveDayRepository extends JpaRepository<DiveDayEntity, Integer> {
}
