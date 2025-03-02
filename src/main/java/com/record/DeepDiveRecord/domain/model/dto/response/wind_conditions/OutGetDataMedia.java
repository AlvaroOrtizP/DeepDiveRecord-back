package com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutGetDataMedia {
    private Integer month;
    private Integer day;
    private Integer dayOfYear;
    private Integer year;
    private String site;
    private Integer timeOfDay;
    private String category;

    //Viento
    private Integer minWinter;
    private Integer maxWinter;

    private BigDecimal windDirection; //5-2

    //Rachas de viento
    private double minGustsOfWind;
    private double maxGustsOfWind;

    //Altura olas
    private double minWaveHeight;
    private double maxWaveHeight;

    //Periodo de olas
    private Integer minWavePeriod;
    private Integer maxWavePeriod;

    //Direccion de olas
    private Integer waveDirection;

    // Temperatura en tierra
    private Integer minEarthTemperature;
    private Integer maxEarthTemperature;

    //Temperatura en agua
    private String minWaterTemperature;
    private String maxWaterTemperature;

    private Integer f;
    private String description;
}
