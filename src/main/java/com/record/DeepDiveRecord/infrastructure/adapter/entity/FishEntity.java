package com.record.DeepDiveRecord.infrastructure.adapter.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "fish")
public class FishEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String site;
    @Column(name = "first_sighting")
    private String firstSighting;
    @Column(name = "first_last")
    private String firstLast;
    @Column(name = "start_season")
    private String startSeason;
    @Column(name = "end_season")
    private String endSeason;
    @Column(name = "first_life_warning")
    private String firstLifeWarning;

}