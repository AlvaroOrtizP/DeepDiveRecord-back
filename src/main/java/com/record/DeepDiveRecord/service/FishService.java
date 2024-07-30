package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.api.domain.fish.getallfishes.FishResponse;
import com.record.DeepDiveRecord.controller.DiveDayController;
import com.record.DeepDiveRecord.entity.FishEntity;
import com.record.DeepDiveRecord.mapper.FishMapper;
import com.record.DeepDiveRecord.repository.FishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishService.class);

    FishRepository fishRepository;
    FishMapper fishMapper;

    public FishService(FishRepository fishRepository, FishMapper fishMapper){
        this.fishRepository = fishRepository;
        this.fishMapper = fishMapper;
    }

    public List<FishResponse> getAllFishList(){
        List<FishResponse> res = new ArrayList<>();
        List<FishEntity> fishEntityList = fishRepository.findAll();

        for(FishEntity item : fishEntityList){
            res.add(fishMapper.fromEntity(item));
        }
        return res;
    }
}
