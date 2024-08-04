package com.record.DeepDiveRecord.infrastructure.adapter.repository.windconditions;


import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WindConditionsCustomRepository {
    Page<WindConditionsEntity> customWindConditionsSearch(Integer fromYear, Integer fromMonth, Integer fromDay,
                                                          Integer toYear, Integer toMonth, Integer toDay,
                                                          String site, Pageable pageable);
}