package com.record.DeepDiveRecord.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InDeepDayLogger {
    private String site;
    private String day;
    private String month;
    private String year;
    private String startTime;
    private String endTime;
    private List<InFishing> fishingList;
    private String notas;
}
