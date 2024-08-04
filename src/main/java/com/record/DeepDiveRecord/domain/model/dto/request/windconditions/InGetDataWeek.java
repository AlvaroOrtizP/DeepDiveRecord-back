package com.record.DeepDiveRecord.domain.model.dto.request.windconditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InGetDataWeek {

    //@JsonProperty("page")
    private Integer page;

    //@JsonProperty("size")
    private Integer size;

    //@JsonProperty("site")
    private String site;

    //@JsonProperty("fromYear")
    private Integer fromYear;
    //@JsonProperty("fromMonth")
    private Integer fromMonth;
    //@JsonProperty("fromDay")
    private Integer fromDay;

    //@JsonProperty("toYear")
    private Integer toYear;
    //@JsonProperty("toMonth")
    private Integer toMonth;
    //@JsonProperty("toDay")
    private Integer toDay;
}