package com.record.DeepDiveRecord.core.windconditions.domain.deletewindconditionsbydays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InDataZoneRangeDeleteWC {
    private String site;

    private String fromYear;
    private String fromMonth;
    private String fromDay;

    private String toYear;
    private String toMonth;
    private String toDay;
}
