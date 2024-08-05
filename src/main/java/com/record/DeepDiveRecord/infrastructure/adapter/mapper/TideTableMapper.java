package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.response.diveday.TideTableResponse;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableId;
import org.springframework.stereotype.Component;

@Component
public interface TideTableMapper {
    TideTableId entityIdFromDiveDayEntity(DiveDayEntity input);
    TideTableResponse responseFromEntity(TideTableEntity input);
}
