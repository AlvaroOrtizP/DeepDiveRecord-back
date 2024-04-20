package com.record.DeepDiveRecord.entity;

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
@Table(name = "fishing")
public class FishingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String apuntes;
    private boolean pescado;

    @ManyToOne
    @JoinColumn(name = "dive_day_id")
    private DiveDayEntity diveDay;

    }
