package com.record.DeepDiveRecord.util;

import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.create.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.request.fishing.edit.InEditFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.dive_day.DiveDayResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.create.FishingDetails;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    public static final  FishingEntity getFishingEntitySuccess(){
        FishingEntity fishingEntitySuccess = new FishingEntity();
        fishingEntitySuccess.setId(1);
        fishingEntitySuccess.setNotes("En ese lugar hay una piedra que tiene por la mitad una abertura pequeña. El pez se encontraba fuera de la abertura pero luego entro");
        fishingEntitySuccess.setCaught(true);
        fishingEntitySuccess.setWeight(new BigDecimal("0.450"));
        fishingEntitySuccess.setLatG(19.88);
        fishingEntitySuccess.setLatG(24.11);
        fishingEntitySuccess.setGeographicalLocation(getGeographicalLocationSuccess());
        fishingEntitySuccess.setFish(getFishEntitySuccess());
        return fishingEntitySuccess;
    }
    public static final DiveDayEntity getDiveDayEntitySuccess(){
        DiveDayEntity res = new DiveDayEntity();
        res.setDiveDayId(1);
        res.setDay("31");
        res.setBeginning("17:00");
        res.setEnd("21:00");
        res.setSite("487006");
        res.setNotes("Dia con Mario, nos metimos por Pesca la Viej....");
        res.setYear("2024");
        res.setMonth("7");
        res.setAssessment(3);
        res.setGeographicalLocation(getGeographicalLocationSuccess());
        res.setJellyfish(1);
        res.setVisibility(1);
        res.setSeaBackground(3);
        res.setFishGrass(1);
        res.setPresencePlastic(1);

        return res;
    }
    public static final GeographicalLocationEntity getGeographicalLocationSuccess() {
        GeographicalLocationEntity entity = new GeographicalLocationEntity();
        entity.setId(2);
        entity.setName("Acantilado-Isla-Este");
        entity.setSite("Isla");
        entity.setIdWindwuru("487006");
        return entity;
    }

    public static final FishEntity getFishEntitySuccess(){
        FishEntity fish = new FishEntity();
        fish.setId(2);
        fish.setName("Cabracho");
        fish.setSite("Cantabria");
        fish.setFirstSighting("22/04");
        fish.setFirstLast("31/09");
        fish.setStartSeason("01/05");
        fish.setEndSeason("31/09");
        fish.setFirstLifeWarning("22/04/2023");
        return fish;
    }



    public static final FishingEntity getAFishingEntity(){
        FishingEntity entity = new FishingEntity();
        entity.setId(1);
        entity.setNotes("prueba");
        entity.setCaught(true);
        entity.setWeight(new BigDecimal("12.2"));
        entity.setSightingTime("18:10");
        entity.setLatG(41.2);
        entity.setLongG(42.3);
        FishEntity fish = new FishEntity();
        fish.setId(1);
        fish.setName("Pez Ballesta");
        fish.setSite("Isla");
        fish.setFirstSighting("0105");
        fish.setFirstLast("1212");
        fish.setStartSeason("1109");
        fish.setEndSeason("1412");
        fish.setFirstLifeWarning("12122024");
        entity.setFish(fish);
        entity.setLongG(2);
        entity.setLatG(2);
        GeographicalLocationEntity geographicalLo = new GeographicalLocationEntity();
        geographicalLo.setId(1);
        geographicalLo.setName("Isla");
        geographicalLo.setSite("Acantilado-Oeste");
        entity.setGeographicalLocation(geographicalLo);
        return entity;
    }
    public static final FishingDetails getAFishingDetails(FishingEntity input){
        FishingDetails res = new FishingDetails();
        res.setId(input.getId());
        res.setNotes(input.getNotes());
        res.setCaught(input.isCaught());
        res.setWeight(input.getWeight());
        res.setLatG(input.getLatG());
        res.setLogG(input.getLongG());
        res.setFishId(input.getFish().getId());
        res.setName(input.getFish().getName());
        res.setSite(input.getFish().getSite());
        res.setFirstSighting(input.getFish().getFirstSighting());
        res.setFirstLast(input.getFish().getFirstLast());
        res.setStartSeason(input.getFish().getStartSeason());
        res.setEndSeason(input.getFish().getEndSeason());
        res.setFirstLifeWarning(input.getFish().getFirstLifeWarning());
        res.setGeographieId(input.getGeographicalLocation().getId());
        res.setGeographieName(input.getGeographicalLocation().getName());
        res.setGeographieSite(input.getGeographicalLocation().getSite());
        return res;
    }
    public static final FindGeographicalLocation fromRequestToDtoFind(String name, String site) {
        FindGeographicalLocation res = new FindGeographicalLocation();
        res.setName(name);
        res.setSite(site);
        return res;
    }
    public static final Page<DiveDayEntity> getDiveDayPage(){
        List<DiveDayEntity> list = new ArrayList<>();
        list.add(getDiveDayEntitySuccess());
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.fromString("asc"), "fecha");
        Page<DiveDayEntity> res = new PageImpl<>(list, pageable, 1);
        return res;
    }

    public static final InCreateFishing getInCreateFishing(){
        InCreateFishing res = new InCreateFishing();
        res.setFishId(1);
        res.setCaught(false);
        res.setName("Acantilado-Oeste");
        res.setSite("Isla");
        res.setNotes("Sargo en la rompiente");
        res.setWeight(new BigDecimal("0.450"));
        res.setLatG(42.2);
        res.setLongG(46.3);
        res.setIdDiveDay(2);
        res.setSightingTime("18:10");

        return res;
    }

    public static final InEditFishing getInEditFishing(){
        InEditFishing res = new InEditFishing();
        res.setFishingId(2);
        res.setFishId(2);
        res.setCaught(false);
        res.setName("Acantilado-Este");
        res.setSite("Noja");
        res.setNotes("Sargo en la rompiente");
        res.setWeight(new BigDecimal("0.450"));
        res.setLatG(42.2);
        res.setLongG(46.3);
        res.setIdDiveDay(2);
        res.setSightingTime("18:10");

        return res;
    }

}
