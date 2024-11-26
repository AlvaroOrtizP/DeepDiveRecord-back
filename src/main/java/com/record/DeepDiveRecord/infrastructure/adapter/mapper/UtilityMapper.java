package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

@Component
public interface UtilityMapper {
    String getCellValueAsString(Cell cell);
    Double getCellValueAsDouble(Cell cell);
    Integer getCellValueAsInteger(Cell cell);
    Boolean getCellValueAsBoolean(Cell cell);

}
