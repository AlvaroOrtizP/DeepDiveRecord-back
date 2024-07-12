package com.record.DeepDiveRecord.dto.request;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
