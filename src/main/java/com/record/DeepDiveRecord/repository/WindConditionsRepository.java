package com.record.DeepDiveRecord.repository;

import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindConditionsRepository extends JpaRepository<WindConditionsEntity, Long> {

}