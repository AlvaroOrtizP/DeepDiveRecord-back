package com.record.DeepDiveRecord.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "dive_day_id")
    private Integer diveDayId;

    @Column(name = "day")
    private String day;

    @Column(name = "beginning")
    private String beginning;

    @Column(name = "end")
    private String end;

    //Hora de la estimacion
    @Column(name = "site")
    private String site;//Cambiarlo por algo identificatiovo para el id de windwuru
    //añadir site y name para luego poder hacer el filtro

    @Column(name = "notes")
    private String notes;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "assessment")
    private Integer assessment;

    @ManyToOne
    @JoinColumn(name = "id_geographic", nullable = false)
    private GeographicalLocationEntity geographicalLocation;

    @Column(name = "presence_of_jellyfish")
    private Integer jellyfish;

    @Column(name = "water_visibility")
    private Integer visibility;

    @Column(name = "sea_Background")
    private Integer seaBackground;

    @Column(name = "fish_grass")
    private Integer fishGrass;

    @Column(name = "presence_plastic")
    private Integer presencePlastic;
    @OneToMany(mappedBy = "diveDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FishingEntity> fishingEntities;


}