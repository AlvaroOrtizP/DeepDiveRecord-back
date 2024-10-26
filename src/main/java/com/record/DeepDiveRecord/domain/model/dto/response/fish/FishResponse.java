package com.record.DeepDiveRecord.domain.model.dto.response.fish;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FishResponse {
    private Integer id;
    private String name;
    private String site;
    private String firstSighting;
    private String firstLast;
    private String startSeason;
    private String endSeason;
    private String firstLifeWarning;
}