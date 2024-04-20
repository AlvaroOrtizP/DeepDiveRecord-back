package com.record.DeepDiveRecord.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tide_table")
public class TideTableEntity {
    @EmbeddedId
    private TideTableId id;
    @Column(name = "moon_phase")
    private Integer moonPhase;

    @Column(name = "morning_high_tide_time")
    private String morningHighTideTime;
    @Column(name = "morning_high_tide_height")
    private double morningHighTideHeight;

    @Column(name = "afternoon_high_tide_time")
    private String afternoonHighTideTime;
    @Column(name = "afternoon_high_tide_height")
    private double afternoonHighTideHeight;

    @Column(name = "morning_low_tide_time")
    private String morningLowTideTime;
    @Column(name = "morning_low_tide_height")
    private double morningLowTideHeight;

    @Column(name = "afternoon_low_tide_time")
    private String afternoonLowTideTime;
    @Column(name = "afternoon_low_tide_height")
    private double afternoonLowTideHeight;

}
