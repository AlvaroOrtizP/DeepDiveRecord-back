package com.record.DeepDiveRecord.core.model.fishing;

import com.record.DeepDiveRecord.core.model.common.Checker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutDeleteFishingDay {
    private Integer id;
    private String day;
    private String month;
    private String year;
    private String site;
    private Checker checker;
}
