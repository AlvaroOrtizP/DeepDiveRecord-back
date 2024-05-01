package com.record.DeepDiveRecord.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyDivingRequest {
    private String day;
    private String beginning;
    private String end;
    private String site;
    private String notes;
    private String year;
    private String month;
    private List<FishingRequest> fishingRequestList;



}
