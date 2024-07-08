package com.record.DeepDiveRecord.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class WindConditionsId implements Serializable {
    @Column(name ="year")
    private Integer year;
    private Integer month;
    @Column(name ="day")
    private Integer day;
    //Hora de la estimacion
    @Column(name = "time_of_day")
    private Integer time;
    private String site;
}
