package com.record.DeepDiveRecord.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "geographical_location")
public class GeographicalLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "site", nullable = false, length = 40)
    private String site;

    @Column(name = "id_windwuru", nullable = false, length = 50)
    private String idWindwuru;

    @OneToMany(mappedBy = "geographicalLocation")
    private Set<DiveDayEntity> diveDays;
}