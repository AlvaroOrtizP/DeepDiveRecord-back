package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.api.domain.fishing.createfishing.InCreateFishing;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.repository.FishingRepository;
import com.record.DeepDiveRecord.repository.GeographicalLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FishingService.class);
    private FishingRepository fishingRepository;
    private GeographicalLocationRepository geographicalLocationRepository;
    private FishingMapper fishingMapper;

    public FishingService(FishingRepository fishingRepository, FishingMapper fishingMapper, GeographicalLocationRepository geographicalLocationRepository) {
        this.fishingRepository = fishingRepository;
        this.fishingMapper = fishingMapper;
        this.geographicalLocationRepository = geographicalLocationRepository;
    }

    public Integer createFishing(InCreateFishing input) {
        List<GeographicalLocationEntity> list =  geographicalLocationRepository.findAll();
        Integer id = 0;
        for(GeographicalLocationEntity item : list){
            if(item.getName().equals(input.getName()) && item.getSite().equals(input.getSite())){
                id= item.getId();
                break;
            }
        }
        FishingEntity fishingEntity = fishingMapper.fromApiDomain(input);

        GeographicalLocationEntity geograficEntity = new GeographicalLocationEntity();
        geograficEntity.setId(id);
        fishingEntity.setGeographicalLocation(geograficEntity);



        FishingEntity res = fishingRepository.save(fishingEntity);
        return res.getId();
    }
}
