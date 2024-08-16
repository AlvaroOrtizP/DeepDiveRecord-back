package com.record.DeepDiveRecord.domain.model.dto.port.wind_condition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindDeepDiveDataByDays {
    private Integer fromYear;
    private Integer fromMonth;
    private Integer fromDay;
    private Integer toYear;
    private Integer toMonth;
    private Integer toDay;
    private String site;
    private Integer page;
    private Integer size;

}
