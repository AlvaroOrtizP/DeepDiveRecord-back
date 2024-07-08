package com.record.DeepDiveRecord.core.model.windconditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InDataZoneRangeWC {
    private String site;

    private Integer fromYear;
    private Integer fromMonth;
    private Integer fromDay;

    private Integer toYear;
    private Integer toMonth;
    private Integer toDay;
}
