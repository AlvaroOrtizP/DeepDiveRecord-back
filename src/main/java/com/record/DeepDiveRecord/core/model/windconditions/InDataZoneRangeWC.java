package com.record.DeepDiveRecord.core.model.windconditions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InDataZoneRangeWC {
    private String site;

    private Integer fromYear;
    private Integer fromMonth;
    private Integer fromDay;

    private Integer toYear;
    private Integer toMonth;
    private Integer toDay;
}
