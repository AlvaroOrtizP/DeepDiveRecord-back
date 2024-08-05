package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableId;

import java.util.Optional;

public interface TideTablePort {
    Optional<TideTableEntity>  findById(TideTableId input);
}
