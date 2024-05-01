package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.core.model.common.TideRegister;
import com.record.DeepDiveRecord.core.model.tidetable.InDeleteTideRegister;
import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.entity.TideTableId;
import org.springframework.stereotype.Component;

@Component
public interface TideRegisterMapper {
    TideTableEntity fromCreateCoreToEntity(TideRegister input);
    TideTableId fromDeleteCoreToEntityId(InDeleteTideRegister input);

}
