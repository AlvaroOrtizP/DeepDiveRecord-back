package com.record.DeepDiveRecord.domain.model.dto.response.diveday;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TideTableResponse {
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer site;
    private Integer moonPhase;
    private Integer coefficient0H;
    private Integer coefficient12H;
    private String morningHighTideTime;
    private String morningHighTideHeight;
    private String afternoonHighTideTime;
    private String afternoonHighTideHeight;
    private String morningLowTideTime;
    private String morningLowTideHeight;
    private String afternoonLowTideTime;
    private String afternoonLowTideHeight;
}