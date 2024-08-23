package com.record.DeepDiveRecord.domain.model.dto.response.dive_day;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiveDayResponse {
    private Integer id;
    private String date;
    private String site;
    private Integer assessment;
}
