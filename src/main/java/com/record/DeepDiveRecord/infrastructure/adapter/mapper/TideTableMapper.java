package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.port.tide_table.FindTideTable;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.TideTableResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableId;
import org.springframework.stereotype.Component;

@Component
public interface TideTableMapper {
    FindTideTable dtoPortFromDiveDayEntity(DiveDayEntity input);
    TideTableResponse responseFromEntity(TideTableEntity input);
    TideTableId entityIdFromDtoPort(FindTideTable input);
}
