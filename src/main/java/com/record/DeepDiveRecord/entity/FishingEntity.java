package com.record.DeepDiveRecord.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "fishing")
public class FishingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String notes;
    private boolean caught;
    private double weight;
    @ManyToOne
    @JoinColumn(name = "fish_id", referencedColumnName = "id")
    private FishEntity fish;


}
