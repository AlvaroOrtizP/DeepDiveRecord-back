package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.FishUseCase;
import com.record.DeepDiveRecord.domain.model.dto.Fish;
import com.record.DeepDiveRecord.domain.model.dto.request.fish.create.FishCreateRequest;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationRepositoryPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishMapper;
import com.record.DeepDiveRecord.domain.port.FishRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishService implements FishUseCase {

    @Autowired
    private FishMapper fishMapper;

    @Autowired
    private FishRepositoryPort fishRepositoryPort;

    @Autowired
    private GeographicalLocationRepositoryPort geographicalLocationRepositoryPort;

    @Override
    public Integer createFish(FishCreateRequest input) {
        GeographicalLocationEntity location = geographicalLocationRepositoryPort.findByNameAndSite(/*input.getName(), input.getSite()*/"", "");
        if (location == null) {
            throw new IllegalArgumentException("Geographical location not found");
        }

        Fish fish = fishMapper.modelFromRequest(input);
        //fish.setGeographicalLocation(location);
        return fishRepositoryPort.save(fish);
    }
}