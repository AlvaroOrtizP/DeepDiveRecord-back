package com.record.DeepDiveRecord.core.model.tidetable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InDeleteTideRegister {
    private String day;
    private String month;
    private String year;
    private String site;
}
