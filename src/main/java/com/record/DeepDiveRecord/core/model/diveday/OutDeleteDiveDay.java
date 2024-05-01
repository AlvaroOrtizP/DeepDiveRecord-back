package com.record.DeepDiveRecord.core.model.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutDeleteDiveDay {
    private Integer diveDayid;
    private Checker checker;
}
