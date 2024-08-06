package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.FishUseCase;
import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import com.record.DeepDiveRecord.domain.port.FishPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishService implements FishUseCase {

    @Autowired
    private FishMapper fishMapper;

    @Autowired
    private FishPort fishRepositoryPort;

    @Override
    public List<FishResponse> getAllFishList() {
        List<FishEntity> entityList = fishRepositoryPort.getAllFishList();
        List<FishResponse> res = new ArrayList<>();
        for(FishEntity item : entityList){
            res.add(fishMapper.fromEntity(item));
        }
        return res;
    }
}