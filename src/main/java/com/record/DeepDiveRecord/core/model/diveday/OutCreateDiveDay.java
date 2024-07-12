package com.record.DeepDiveRecord.core.model.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.DiveDay;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutCreateDiveDay {
    private DiveDay diveDay;
    private Checker checker;
}
