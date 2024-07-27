package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.TideTableResponse;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.entity.TideTableId;
import org.springframework.stereotype.Component;

@Component
public interface TideTableMapper {
    TideTableId entityIdFromDiveDayEntity(DiveDayEntity input);
    TideTableResponse responseFromEntity(TideTableEntity input);

}
