package com.record.DeepDiveRecord.core.model.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutDeleteDiveDay {
    private Integer diveDayid;
    private Checker checker;
}
