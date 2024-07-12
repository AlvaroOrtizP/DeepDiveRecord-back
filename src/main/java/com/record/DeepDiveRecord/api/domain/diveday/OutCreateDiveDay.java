package com.record.DeepDiveRecord.api.domain.diveday;

import com.record.DeepDiveRecord.core.model.common.Fishing;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutCreateDiveDay {
    private Integer diveDayId;
    private String day;
    private String beginning;
    private String end;
    private String site;
    private String notes;
    private String year;
    private String month;
    private List<Fishing> fishingList;

    private boolean checker;
    private String message;
}