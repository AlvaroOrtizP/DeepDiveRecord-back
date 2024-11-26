package com.record.DeepDiveRecord.infrastructure.adapter.repository.configuration_data;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.ConfigurationDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ConfigurationDataRepository extends JpaRepository<ConfigurationDataEntity, Integer> {

}
