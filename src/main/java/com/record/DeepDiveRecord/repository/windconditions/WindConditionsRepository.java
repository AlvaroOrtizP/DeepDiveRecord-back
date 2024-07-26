package com.record.DeepDiveRecord.repository.windconditions;

import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.entity.WindConditionsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindConditionsRepository extends JpaRepository<WindConditionsEntity, WindConditionsId> {

}