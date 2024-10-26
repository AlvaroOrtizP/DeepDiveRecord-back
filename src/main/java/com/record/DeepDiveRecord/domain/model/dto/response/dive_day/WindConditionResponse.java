package com.record.DeepDiveRecord.domain.model.dto.response.dive_day;

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
    private Integer dayOfYear;
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
    private String waterTemperature;
    private Integer conditionCode;
    private String conditionDescription;
}