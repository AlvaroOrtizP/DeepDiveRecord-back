package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.api.domain.windconditions.Pagination;
import com.record.DeepDiveRecord.core.model.windconditions.OutDailyStatistics;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface PaginationMapper {
    Pagination toAPIDomain(Page<OutDailyStatistics> res);


}
