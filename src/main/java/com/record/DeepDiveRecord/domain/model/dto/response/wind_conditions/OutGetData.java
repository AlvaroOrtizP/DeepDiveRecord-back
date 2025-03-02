package com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutGetData {
    private Integer month;
    private Integer day;
    private Integer dayOfYear;
    private Integer year;
    private String site;
    private String timeOfDay;

    //Viento
    private Integer wind;

    private BigDecimal windDirection; //5-2

    //Rachas de viento
    private double gustsOfWind;

    //Altura olas
    private double waveHeight;

    //Periodo de olas
    private Integer wavePeriod;

    //Direccion de olas
    private Integer waveDirection;

    // Temperatura en tierra
    private Integer earthTemperature;

    //Temperatura en agua
    private String waterTemperature;

    private Integer f1;
    private String description1;
    private Integer f2;
    private String description2;


}