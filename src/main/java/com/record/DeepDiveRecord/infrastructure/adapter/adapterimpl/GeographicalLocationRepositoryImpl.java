package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.GeographicalLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GeographicalLocationRepositoryImpl implements GeographicalLocationPort {

    @Autowired
    private GeographicalLocationRepository geographicalLocationRepository;

    @Override
    public GeographicalLocationEntity findByNameAndSite(String name, String site) {
        List<GeographicalLocationEntity> list =  geographicalLocationRepository.findAll();
        for(GeographicalLocationEntity item : list){
            if(item.getName().equals(name) && item.getSite().equals(site)){
               return item;
            }
        }
        return null;
    }

    @Override
    public List<GeographicalLocationEntity> getAllGeGeographicalLocation() {
        return geographicalLocationRepository.findAll();
    }

    @Override
    public Optional<GeographicalLocationEntity> findById(Integer idGeograficLocation) {
        return geographicalLocationRepository.findById(idGeograficLocation);
    }
}