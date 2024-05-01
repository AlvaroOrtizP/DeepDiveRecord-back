package com.record.DeepDiveRecord.core.model.tidetable;

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
public class InCreateTideRegister {
    private List<TideRegister> tideRegisterList;

}
