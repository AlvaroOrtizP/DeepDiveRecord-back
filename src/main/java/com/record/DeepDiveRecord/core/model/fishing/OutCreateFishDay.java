package com.record.DeepDiveRecord.core.model.fishing;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.Fishing;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutCreateFishDay {
    private Fishing fishing;
    private Checker checker;
}
