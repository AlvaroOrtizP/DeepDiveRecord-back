package com.record.DeepDiveRecord.repository;

import com.record.DeepDiveRecord.entity.DiveDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiveDayRepository extends JpaRepository<DiveDayEntity, Integer> {


}
