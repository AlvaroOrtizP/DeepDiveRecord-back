package com.record.DeepDiveRecord.core.model.fishing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InDeleteFishingDay {
    private Integer id;
    private String site;
    private String day;
    private String month;
    private String year;
    private String startTime;
    private String endTime;
}
