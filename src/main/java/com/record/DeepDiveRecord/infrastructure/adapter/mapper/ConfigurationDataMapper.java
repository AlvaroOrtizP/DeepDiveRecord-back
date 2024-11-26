package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.ConfigurationDataEntity;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public interface ConfigurationDataMapper {
    ConfigurationDataEntity mapRowToConfigurationDataEntity(Row row);
}
