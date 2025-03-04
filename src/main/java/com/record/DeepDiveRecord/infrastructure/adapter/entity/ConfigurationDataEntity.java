package com.record.DeepDiveRecord.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "configuration_data")
public class ConfigurationDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "active")
    private boolean active;
    @Column(name = "id_aemet")
    private String idAemet;
    @Column(name = "id_windwuru")
    private int idWindwuru;

}
