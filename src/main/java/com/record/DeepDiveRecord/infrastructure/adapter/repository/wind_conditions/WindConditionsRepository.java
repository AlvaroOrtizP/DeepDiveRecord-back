package com.record.DeepDiveRecord.infrastructure.adapter.repository.wind_conditions;


import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindConditionsRepository extends JpaRepository<WindConditionsEntity, WindConditionsId> {

}