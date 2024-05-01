package com.record.DeepDiveRecord.core.model.tidetable;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.TideRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutUpdateRegister {
    private TideRegister tideRegisterNew;
    private Checker checker;
}
