package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FishRepositoryImpl implements FishPort {

    @Autowired
    private FishRepository fishRepository;

    @Autowired
    private FishMapper fishMapper;

    @Override
    public Integer save(Fish fish) {
        FishEntity fishEntity = fishMapper.entityFromModel(fish);
        FishEntity savedEntity = fishRepository.save(fishEntity);
        return savedEntity.getId();
    }
}