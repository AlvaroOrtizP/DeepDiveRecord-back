package com.record.DeepDiveRecord.core.model.windconditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InDataDeleteWC {
    private String site;

    private String fromYear;
    private String fromMonth;
    private String fromDay;
    private String fromTime;
}
