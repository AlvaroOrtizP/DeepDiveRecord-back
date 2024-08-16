package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.response.common.Pagination;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.CommonMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CommonMapperImpl implements CommonMapper {
    @Override
    public Pagination getPagination(Page<WindConditionsEntity> outGetDataListsEntity) {
        Pagination pagination = new Pagination();
        pagination.setTotalPages(outGetDataListsEntity.getTotalPages());
        pagination.setTotalElements((int) outGetDataListsEntity.getTotalElements());
        pagination.setSize(outGetDataListsEntity.getSize());
        pagination.setNumber(outGetDataListsEntity.getNumber());
        pagination.setNumberOfElements(outGetDataListsEntity.getNumberOfElements());
        pagination.setIsLast(outGetDataListsEntity.isLast());
        pagination.setIsFirst(outGetDataListsEntity.isFirst());
        pagination.setIsEmpty(outGetDataListsEntity.isEmpty());
        return pagination;
    }
}
