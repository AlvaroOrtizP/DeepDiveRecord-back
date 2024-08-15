package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.dto.port.tide_table.FindTideTable;
import com.record.DeepDiveRecord.domain.port.TideTablePort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.TideTableMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.tide_table.TideTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class TideTableRepositoryImpl implements TideTablePort {
    @Autowired
    TideTableRepository tideTableRepository;
    @Autowired
    TideTableMapper tideTableMapper;
    @Override
    public Optional<TideTableEntity> findById(FindTideTable input) {

        return  tideTableRepository.findById(tideTableMapper.entityIdFromDtoPort(input));
    }
}
