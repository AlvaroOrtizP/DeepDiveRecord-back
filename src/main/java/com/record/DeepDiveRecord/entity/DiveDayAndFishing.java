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
@Table(name = "dive_day_and_fishing")
public class DiveDayAndFishing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_fishing", referencedColumnName = "id")
    private FishingEntity fishing;


    @ManyToOne
    @JoinColumn(name = "dive_day_id", referencedColumnName = "dive_day_id")
    private DiveDayEntity diveDay;
}
