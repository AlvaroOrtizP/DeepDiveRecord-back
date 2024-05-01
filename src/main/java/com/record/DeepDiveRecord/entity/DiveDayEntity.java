package com.record.DeepDiveRecord.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dive_day")
public class DiveDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="dive_day_id")
    private Integer diveDayId;

    @Column(name ="year")
    private String year;
    private String month;
    @Column(name ="day")
    private String day;
    //Hora de la estimacion
    @Column(name = "site")
    private String site;
    private String beginning;
    private String end;
    private String notas;
    @OneToMany(mappedBy = "diveDay", cascade = CascadeType.ALL)
    private List<FishingEntity> fishingEntityList;

   }
