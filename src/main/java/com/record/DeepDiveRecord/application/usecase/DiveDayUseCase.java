package com.record.DeepDiveRecord.application.usecase;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayDetailsResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiveDayUseCase {
    Integer createDiveDay(InCreateDailyDiving inCreateDailyDiving);
    DiveDayDetailsResponse findDiveDayById(Integer id);
    Page<DiveDayResponse> findByFilters(String zona, Pageable pageable);
}
