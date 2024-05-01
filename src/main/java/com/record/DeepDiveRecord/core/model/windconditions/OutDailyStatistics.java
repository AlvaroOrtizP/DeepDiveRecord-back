package com.record.DeepDiveRecord.core.model.windconditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutDailyStatistics {
    String year;
    String month;
    String day;
    String site;
    String timeOfDay;
    Integer wind;
    BigDecimal windDirection; //5-2
    Double gustsOfWind;
    String waveHeight;
    Integer wavePeriod;
    Integer earthTemperature;
    String waterTtermperature;
    Integer f1;
    String descripcion1;
    Integer f2;
    String descripcion2;
    String beginning;
    String end;
    String notas;
    Integer diveDayId;

    public boolean isGoodDay() {
        return diveDayId != null;
    }
}
