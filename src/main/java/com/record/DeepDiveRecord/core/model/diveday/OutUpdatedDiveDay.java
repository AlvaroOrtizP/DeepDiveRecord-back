package com.record.DeepDiveRecord.core.model.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.DiveDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutUpdatedDiveDay {
    private DiveDay diveDayNew;
    private Checker checker;
}
