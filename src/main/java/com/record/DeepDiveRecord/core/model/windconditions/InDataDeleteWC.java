package com.record.DeepDiveRecord.core.model.windconditions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InDataDeleteWC {
    private String site;

    private Integer fromYear;
    private Integer fromMonth;
    private Integer fromDay;
    private String fromTime;
}
