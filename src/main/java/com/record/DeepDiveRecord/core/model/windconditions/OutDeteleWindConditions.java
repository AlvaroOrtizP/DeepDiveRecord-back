package com.record.DeepDiveRecord.core.model.windconditions;

import com.record.DeepDiveRecord.core.model.common.Checker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutDeteleWindConditions {
    private String site;
    private Integer year;
    private Integer month;
    private Integer day;
    private String time;
    private Checker checker;


}