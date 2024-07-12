package com.record.DeepDiveRecord.core.model.tidetable;

import com.record.DeepDiveRecord.core.model.common.TideRegister;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InUpdateregister {
    private TideRegister tideRegisterOld;
    private TideRegister tideRegisterNew;
}
