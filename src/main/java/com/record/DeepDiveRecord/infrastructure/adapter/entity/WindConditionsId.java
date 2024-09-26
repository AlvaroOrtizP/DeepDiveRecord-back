package com.record.DeepDiveRecord.infrastructure.adapter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class WindConditionsId implements Serializable {
    @Column(name ="year")
    private Integer year;

    @Column(name ="day_of_year")
    private Integer dayOfYear;
    //Hora de la estimacion
    @Column(name = "time_of_day")
    private Integer time;
    private String site;
}