package com.record.DeepDiveRecord.infrastructure.adapter.repository.wind_conditions;


import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WindConditionsCustomRepository {
    Page<WindConditionsEntity> customWindConditionsSearch(Integer fromYear, Integer fromMonth, Integer fromDay,
                                                          Integer toYear, Integer toMonth, Integer toDay,
                                                          String site,  boolean onlyImpares, Pageable pageable);
}