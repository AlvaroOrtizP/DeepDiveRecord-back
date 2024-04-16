package com.record.DeepDiveRecord.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "diveday")
public class DiveDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="dive_day_id")
    private Integer diveDayId;

    @Column(name ="year")
    private String year;
    private String month;
    @Column(name ="day")
    private String day;
    //Hora de la estimacion
    @Column(name = "site")
    private String site;
    private String beginning;
    private String end;
    private String notas;
    @OneToMany(mappedBy = "diveDay", cascade = CascadeType.ALL)
    private List<FishingEntity> fishingEntityList;

    public Integer getDiveDayId() {
        return diveDayId;
    }

    public void setDiveDayId(Integer diveDayId) {
        this.diveDayId = diveDayId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<FishingEntity> getFishingEntityList() {
        return fishingEntityList;
    }

    public void setFishingEntityList(List<FishingEntity> fishingEntityList) {
        this.fishingEntityList = fishingEntityList;
    }


    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
