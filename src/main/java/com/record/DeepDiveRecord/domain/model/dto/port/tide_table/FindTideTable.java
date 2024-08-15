package com.record.DeepDiveRecord.domain.model.dto.port.tide_table;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FindTideTable {
    private String day;
    private String month;
    private String year;
    private String site;
}
