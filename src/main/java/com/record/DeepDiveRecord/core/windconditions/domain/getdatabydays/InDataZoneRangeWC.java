package com.record.DeepDiveRecord.core.windconditions.domain.getdatabydays;

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

    private String fromYear;
    private String fromMonth;
    private String fromDay;

    private String toYear;
    private String toMonth;
    private String toDay;
}
