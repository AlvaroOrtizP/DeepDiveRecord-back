package com.record.DeepDiveRecord.core.windconditions.domain.deletewindcondition;

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
    private String year;
    private String month;
    private String day;
    private String time;
    private boolean delete;
    private String errorMessage;


}