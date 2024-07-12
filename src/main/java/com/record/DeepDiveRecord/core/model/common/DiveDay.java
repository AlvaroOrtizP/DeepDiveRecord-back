package com.record.DeepDiveRecord.core.model.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiveDay {
    private Integer diveDayId;
    private String day;
    private String beginning;
    private String end;
    private String site;
    private String notes;
    private String year;
    private String month;
    private List<Fishing> fishingList;
}
