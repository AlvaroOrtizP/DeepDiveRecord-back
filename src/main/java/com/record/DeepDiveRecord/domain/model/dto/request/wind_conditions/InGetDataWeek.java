package com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InGetDataWeek {

    private Integer page;
    private Integer size;
    private String site;
    private Integer fromYear;
    private Integer fromMonth;
    private Integer fromDay;
    private Integer toYear;
    private Integer toMonth;
    private Integer toDay;
}