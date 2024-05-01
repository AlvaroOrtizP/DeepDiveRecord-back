package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.InDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.dto.request.DailyDivingRequest;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import org.springframework.stereotype.Component;

@Component
public interface DiveDayMapper {
    DiveDayEntity fromCreateCoreToEntity(InCreateDiveDay input);
    InCreateDiveDay fromRequestToCore(DailyDivingRequest input);
    OutCreateDiveDay fromCoreToOutCore(InCreateDiveDay input);
    DiveDayEntity fromUpdateCoreToEntity(InUpdatedDiveDay input);



}
