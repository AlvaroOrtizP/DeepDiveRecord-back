package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.ConfigurationDataEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsId;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.ConfigurationDataMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.UtilityMapper;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationDataMapperImpl implements ConfigurationDataMapper {
    @Autowired
    private UtilityMapper utilityMapper;
    private static final Logger log = LoggerFactory.getLogger(ConfigurationDataMapperImpl.class);


    @Override
    public ConfigurationDataEntity mapRowToConfigurationDataEntity(Row row) {
        ConfigurationDataEntity entity = new ConfigurationDataEntity();
        try {

            // Asignar valores desde las celdas de la fila a los campos de la entidad
            entity.setId(utilityMapper.getCellValueAsInteger(row.getCell(0)));
            entity.setActive(utilityMapper.getCellValueAsBoolean(row.getCell(1)));
            entity.setIdAemet(utilityMapper.getCellValueAsString(row.getCell(2)));
            entity.setIdWindwuru(utilityMapper.getCellValueAsInteger(row.getCell(3)));
        }catch (Exception e) {
            log.info("Error em el mapRowToConfigurationDataEntity: "+e.getMessage());
        }

        return entity;
    }
}
