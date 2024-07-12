package com.record.DeepDiveRecord.core.model.tidetable;

import com.record.DeepDiveRecord.core.model.common.Checker;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutDeleteTideRegister {
    private String day;
    private String month;
    private String year;
    private String site;
    private Checker checker;


}
