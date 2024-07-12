package com.record.DeepDiveRecord.entity;
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
    private String firstSighting;
    private String firstLast;
    private String startSeason;
    private String endSeason;
    private String firstLifeWarning;

}