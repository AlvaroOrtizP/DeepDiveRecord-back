package com.record.DeepDiveRecord.domain.model.dto.response.windconditions;

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
    Integer year;
    String site;
    String timeOfDay;
    Integer wind;
    BigDecimal windDirection; //5-2
    Double gustsOfWind;
    String waveHeight;
    Integer wavePeriod;
    Integer earthTemperature;
    String waterTtermperature;
    Integer f1;
    String descripcion1;
    Integer f2;
    String descripcion2;
    String beginning;
    String end;
    String notas;
}