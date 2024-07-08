package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.windconditions.Pagination;
import com.record.DeepDiveRecord.core.model.windconditions.OutDailyStatistics;
import com.record.DeepDiveRecord.mapper.PaginationMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationMapperImpl implements PaginationMapper {
    @Override
    public Pagination toAPIDomain(Page<OutDailyStatistics> res) {
        Pagination pagination = new Pagination();
        pagination.setTotalPages(res.getTotalPages());
        pagination.setTotalElements((int) res.getTotalElements());
        pagination.setSize(res.getSize());
        pagination.setNumber(res.getNumber());
        pagination.setNumberOfElements(res.getNumberOfElements());
        pagination.setIsLast(res.isLast());
        pagination.setIsFirst(res.isFirst());
        pagination.setIsEmpty(res.isEmpty());

        return pagination;
    }
}
