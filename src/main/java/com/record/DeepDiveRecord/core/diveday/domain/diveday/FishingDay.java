package com.record.DeepDiveRecord.core.diveday.domain.diveday;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FishingDay {
    private String site;
    private String day;
    private String month;
    private String year;
    private String startTime;
    private String endTime;
    private List<Fishing> fishingList;
    private String notas;
}
