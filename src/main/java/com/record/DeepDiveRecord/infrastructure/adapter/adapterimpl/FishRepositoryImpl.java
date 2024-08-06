package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.port.FishPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FishRepositoryImpl implements FishPort {

    @Autowired
    private FishRepository fishRepository;

    @Autowired
    private FishMapper fishMapper;

    @Override
    public List<FishEntity> getAllFishList() {
        return fishRepository.findAll();
    }
}