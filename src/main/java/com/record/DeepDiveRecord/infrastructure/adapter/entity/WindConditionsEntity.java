package com.record.DeepDiveRecord.infrastructure.adapter.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "wind_conditions")
public class WindConditionsEntity {
    @EmbeddedId
    private WindConditionsId id;
    private Integer month;
    @Column(name ="day")
    private Integer day;
    //Velocidad del viento
    private Integer wind;
    //Direccion del viento
    @Column(name = "wind_direction")
    private BigDecimal windDirection;
    //Indicador de direccion
    @Column(name = "wind_direction_nm")
    private String windDirectionNm;
    //Rafagas de viento
    @Column(name = "gusts_of_wind")
    private double gustsOfWind;
    //Altura de la ola
    @Column(name = "wave_height")
    private double waveHeight;
    //Periodo de olas
    @Column(name = "wave_period")
    private Integer wavePeriod;
    //Direccion de las olas
    @Column(name = "wave_direction")
    private Integer waveDirection;
    //Indicador de direccion
    @Column(name = "wave_direction_nm")
    private String waveDirectionNm;
    //Temperatura en tierra
    @Column(name = "earth_temperature")
    private Integer earthTemperature;

    @Column(name = "water_temperature")
    private Integer waterTemperature;

    @Column(name = "condition_code")
    private Integer codeCondition;

    @Column(name = "condition_description")
    private String conditionDescription;

}