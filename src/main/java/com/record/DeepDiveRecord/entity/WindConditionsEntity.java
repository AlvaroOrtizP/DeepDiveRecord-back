package com.record.DeepDiveRecord.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "windconditions")
public class WindConditionsEntity {
    @EmbeddedId
    private WindConditionsId id;
    //Velocidad del viento
    private int wind;
    @Column(name = "wind_direction")
    private BigDecimal windDirection;
    //Rafagas de viento
    @Column(name = "gusts_of_wind")
    private double gustsOfWind;
    //Altura de la ola
    @Column(name = "wave_height")
    private int waveHeight;

    //Periodo de olas
    @Column(name = "wave_period")
    private int wavePeriod;
    //Temperatura en tierra
    @Column(name = "earth_temperature")
    private int earthTemperature;

    @Column(name = "water_temperature")
    private int waterTemperature;

    @Column(name = "code_condition")
    private int codeCondition;

    @Column(name = "condition_description")
    private int conditionDescription;




}
