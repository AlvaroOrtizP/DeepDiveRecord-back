package com.record.DeepDiveRecord.core.model.fishing;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InDeleteFishingDay {
    private Integer id;
    private String site;
    private String day;
    private String month;
    private String year;
    private String startTime;
    private String endTime;
}
