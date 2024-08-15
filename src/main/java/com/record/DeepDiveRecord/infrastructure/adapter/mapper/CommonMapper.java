package com.record.DeepDiveRecord.infrastructure.adapter.mapper;

import com.record.DeepDiveRecord.domain.model.dto.response.common.Pagination;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface CommonMapper {
    Pagination getPagination(Page<WindConditionsEntity> outGetDataListsEntity);
}
