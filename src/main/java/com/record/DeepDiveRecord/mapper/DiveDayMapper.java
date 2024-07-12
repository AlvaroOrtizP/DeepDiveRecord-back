package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.diveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;

import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import org.springframework.stereotype.Component;

@Component
public interface DiveDayMapper {
    DiveDayEntity fromCreateCoreToEntity(InCreateDiveDay input);

    InCreateDiveDay fromRequestToCore(InCreateDailyDiving input);

    OutCreateDiveDay fromCoreToOutModelCore(InCreateDiveDay input);

    com.record.DeepDiveRecord.api.domain.diveday.OutCreateDiveDay mapFromOutCreateModelToOutCreateApi(OutCreateDiveDay input);
    DiveDayEntity fromUpdateCoreToEntity(InUpdatedDiveDay input);
}
