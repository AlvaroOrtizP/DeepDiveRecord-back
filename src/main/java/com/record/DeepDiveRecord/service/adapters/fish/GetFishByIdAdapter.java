package com.record.DeepDiveRecord.service.adapters.fish;

import com.record.DeepDiveRecord.core.ports.fish.GetFishByIdPort;
import com.record.DeepDiveRecord.entity.FishEntity;
import com.record.DeepDiveRecord.repository.FishRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetFishByIdAdapter implements GetFishByIdPort {
    FishRepository fishRepository;

    public GetFishByIdAdapter(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    @Override
    public FishEntity getFishById(Integer input) {
        Optional<FishEntity> fishOptional = fishRepository.findById(input);

        return fishOptional.get();
    }
}
