package com.record.DeepDiveRecord.core.model.tidetable;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.TideRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutCreateTideRegister {
    private List<TideRegister> tideRegisterList;
    private Checker checker;
}
