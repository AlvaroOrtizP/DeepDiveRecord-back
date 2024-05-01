package com.record.DeepDiveRecord.core.model.tidetable;

import com.record.DeepDiveRecord.core.model.common.TideRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InUpdateregister {
    private TideRegister tideRegisterOld;
    private TideRegister tideRegisterNew;
}
