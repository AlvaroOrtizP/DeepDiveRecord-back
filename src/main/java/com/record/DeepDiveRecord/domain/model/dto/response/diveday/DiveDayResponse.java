package com.record.DeepDiveRecord.domain.model.dto.response.diveday;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiveDayResponse {
    private Integer diveDayId;
    private Integer day;
    private Integer month;
    private Integer year;
    private String beginning;
    private String end;
    private String site;
    private Integer valoracion;
    private String notes;
    private GeographicalLocationResponse geographicalLocationResponse;
    private TideTableResponse tideTableResponse;
    private List<WindConditionResponse> windConditionsList;
    private List<FishingResponse> fishingList;
}
