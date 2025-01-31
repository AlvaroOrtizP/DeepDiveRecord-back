package com.record.DeepDiveRecord.service.data;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    public static final InCreateDailyDiving getCreateDailyDivingOk() {
        InCreateDailyDiving res = new InCreateDailyDiving();
        res.setDay("23");
        res.setMonth("1");
        res.setYear("2020");
        res.setBeginning("12:35");
        res.setEnd("14:50");
        res.setNotes("Esto es una prueba");
        res.setAssessment(1);//Valoracion: muy buena
        res.setIdGeographicLocation(487006);
        res.setJellyfish(1);//sin medusas
        res.setVisibility(3);// metros
        res.setSeaBackground(1);// Sin mar de fondo
        res.setFishGrass(1);//Con pocos peces pasto
        res.setPresencePlastic(1);// Nada

        return res;
    }

    public static final DiveDayEntity getDiveDayEntityOk() {
        DiveDayEntity res = new DiveDayEntity();
        res.setDiveDayId(1);
        res.setDay("12");
        res.setMonth("2");
        res.setYear("2024");
        res.setBeginning("15:20");
        res.setEnd("18:15");
        res.setSite("487006");
        res.setNotes("Esto es una prueba");
        res.setAssessment(3);
        res.setGeographicalLocation(getGeographicalLocationEntityOk());
        res.setJellyfish(1);
        res.setVisibility(2);
        res.setFishGrass(2);
        res.setPresencePlastic(3);
        res.setFishingEntities(getFishingEntityListOk());
        return res;
    }

    public static final GeographicalLocationEntity getGeographicalLocationEntityOk() {
        GeographicalLocationEntity res = new GeographicalLocationEntity();
        res.setId(1);
        res.setName("Acantilado-Isla-Oeste");
        res.setSite("Isla");
        res.setId(487006);
        return res;
    }

    public static final List<FishingEntity> getFishingEntityListOk() {
        List<FishingEntity> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            res.add(new FishingEntity(i, "prueba", true, new BigDecimal("1.37"), getFishEntityOk(1), getGeographicalLocationEntityOk(), 2.3, 96.2, "12:45", null));
        }
        return res;
    }

    public static final FishEntity getFishEntityOk(Integer id) {
        FishEntity res = new FishEntity();
        res.setId(id);
        res.setName("Cabracho");
        res.setSite("Cantabria");
        res.setFirstLifeWarning("22/04");
        res.setFirstLast("31/09");
        res.setStartSeason("01/05");
        res.setEndSeason("31/09");
        res.setFirstSighting("22/04/2023");
        return res;
    }

    public static final TideTableEntity getTideTableEntityOk() {
        TideTableEntity res = new TideTableEntity();
        TideTableId id = new TideTableId();
        id.setSite("487006");
        id.setYear("2024");
        id.setDay("199");
        id.setMonth("9");
        res.setId(id);
        res.setMoonPhase(2);
        res.setCoefficient0H(81);
        res.setCoefficient12H(93);

        res.setMorningHighTideHeight(4.58);
        res.setMorningHighTideTime("5:12");
        res.setAfternoonHighTideTime("17:37");
        res.setAfternoonHighTideHeight(4.37);

        res.setMorningLowTideTime("11:24");
        res.setMorningLowTideHeight(1.22);
        res.setAfternoonLowTideTime("23:36");
        res.setAfternoonLowTideHeight(1.34);

        return res;
    } public static final WindConditionsEntity getWindConditionsEntityOk() {
        WindConditionsEntity res = new WindConditionsEntity();
        WindConditionsId id = new WindConditionsId();
        id.setTime(14);
        id.setDayOfYear(199);
        id.setYear(2024);
        id.setSite("487006");
        res.setId(id);
        res.setMonth(7);
        res.setWind(14);
        res.setWindDirection(new BigDecimal(78));
        res.setWindDirectionNm("ENE");
        res.setGustsOfWind(25);
        res.setWaveHeight(0.8);
        res.setWavePeriod(9);
        res.setWaveDirection(320);
        res.setWaveDirectionNm("NW");
        res.setEarthTemperature(23);
        res.setWaterTemperature(20);
        res.setCodeCondition(120);
        res.setConditionDescription("muy nuboso");

        return res;
    }
}