package com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutGetData {
    Integer month;
    Integer day;
    Integer dayOfYear;
    Integer year;
    String site;
    String timeOfDay;
    Integer wind;
    BigDecimal windDirection; //5-2
    Double gustsOfWind;
    String waveHeight;
    Integer wavePeriod;
    Integer waveDirection;
    Integer earthTemperature;
    String waterTemperature;
    Integer f1;
    String description1;
    Integer f2;
    String description2;
    String beginning;
    String end;
    String notes;
}