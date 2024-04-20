package com.record.DeepDiveRecord.repository.windconditions;

import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface WindConditionsCustomRepository {
    Page<WindConditionsEntity> customWindConditionsSearch(String fromYear, String fromMonth, String fromDay,
                                                                 String toYear, String toMonth, String toDay,
                                                                 String site, Pageable pageable);
}
