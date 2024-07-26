package com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WindConditionResponse {
    private Integer year;
    private Integer month;
    private Integer day;
    private String site;
    private Integer timeOfDay;
    private Integer wind;
    private Integer windDirection;
    private String windDirectionNM;
    private Integer gustsOfWind;
    private String waveHeight;
    private Integer wavePeriod;
    private String waveDirection;
    private String waveDirectionNM;
    private Integer earthTemperature;
    private String waterTermperature;
    private Integer conditionCode;
    private String conditionDescription;
}
