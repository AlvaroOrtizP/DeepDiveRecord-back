package com.record.DeepDiveRecord.domain.port;

import com.record.DeepDiveRecord.domain.model.dto.Fish;

public interface FishPort {
    Integer save(Fish fish);
}