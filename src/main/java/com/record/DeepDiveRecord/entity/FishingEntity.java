package com.record.DeepDiveRecord.entity;

import jakarta.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApuntes() {
        return apuntes;
    }

    public void setApuntes(String apuntes) {
        this.apuntes = apuntes;
    }

    public boolean isPescado() {
        return pescado;
    }

    public void setPescado(boolean pescado) {
        this.pescado = pescado;
    }

    public DiveDayEntity getDiveDay() {
        return diveDay;
    }

    public void setDiveDay(DiveDayEntity diveDay) {
        this.diveDay = diveDay;
    }
}
