package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.domain.model.dto.port.tide_table.FindTideTable;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;

import java.util.Optional;

public interface TideTablePort {
    Optional<TideTableEntity> findById(FindTideTable input);
}
