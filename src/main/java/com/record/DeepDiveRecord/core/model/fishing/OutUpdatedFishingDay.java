package com.record.DeepDiveRecord.core.model.fishing;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.Fishing;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutUpdatedFishingDay {
    private Fishing fishing;

    private Checker checker;;
}
