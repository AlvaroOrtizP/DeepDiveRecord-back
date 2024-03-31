package com.record.DeepDiveRecord.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "windconditions")
public class WindConditionsEntity {
    @EmbeddedId
    private WindConditionsId id;
    //Velocidad del viento
    private int wind;
    //Rafagas de viento
    @Column(name = "gusts_of_wind")
    private double gustsOfWind;
    //Periodo de olas
    @Column(name = "wave_period")
    private int wavePeriod;
    //Temperatura en tierra
    @Column(name = "earth_temperature")
    private int earthTemperature;

    public WindConditionsId getId() {
        return id;
    }

    public void setId(WindConditionsId id) {
        this.id = id;
    }

    public int getWind() {
        return wind;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public double getGustsOfWind() {
        return gustsOfWind;
    }

    public void setGustsOfWind(double gustsOfWind) {
        this.gustsOfWind = gustsOfWind;
    }

    public int getWavePeriod() {
        return wavePeriod;
    }

    public void setWavePeriod(int wavePeriod) {
        this.wavePeriod = wavePeriod;
    }

    public int getEarthTemperature() {
        return earthTemperature;
    }

    public void setEarthTemperature(int earthTemperature) {
        this.earthTemperature = earthTemperature;
    }

    @Override
    public String toString() {
        return "WindConditionsEntity{" +
                "id=" + id +
                ", wind=" + wind +
                ", gustsOfWind=" + gustsOfWind +
                ", wavePeriod=" + wavePeriod +
                ", earthTemperature=" + earthTemperature +
                '}';
    }
}
