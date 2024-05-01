package com.record.DeepDiveRecord.core.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiveDay {
    private Integer diveDayId;
    private String day;
    private String beginning;
    private String end;
    private String site;
    private String notas;
    private String year;
    private String month;
    private List<Fishing> fishingList;
}
