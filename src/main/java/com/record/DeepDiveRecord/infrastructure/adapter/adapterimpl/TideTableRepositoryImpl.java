package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.port.TideTablePort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableId;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.tidetable.TideTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class TideTableRepositoryImpl implements TideTablePort {
    @Autowired
    TideTableRepository tideTableRepository;
    @Override
    public Optional<TideTableEntity> findById(TideTableId input) {

        return  tideTableRepository.findById(input);
    }
}
