package com.record.DeepDiveRecord.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "dive_day")
public class DiveDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dive_day_id")
    private Integer diveDayId;

    @Column(name = "year")
    private String year;
    @Column(name = "month")
    private String month;
    @Column(name = "day")
    private String day;
    //Hora de la estimacion
    @Column(name = "site")
    private String site;
    @Column(name = "beginning")
    private String beginning;
    @Column(name = "end")
    private String end;
    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "diveDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiveDayAndFishingEntity> diveDayAndFishingList;

}
