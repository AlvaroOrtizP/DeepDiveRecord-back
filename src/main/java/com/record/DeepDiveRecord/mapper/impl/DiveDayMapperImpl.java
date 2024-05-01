package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.core.model.common.DiveDay;
import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.InDeleteDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.dto.request.DailyDivingRequest;
import com.record.DeepDiveRecord.dto.request.FishingRequest;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DiveDayMapperImpl implements DiveDayMapper {
    @Override
    public DiveDayEntity fromCreateCoreToEntity(InCreateDiveDay input) {
        DiveDayEntity diveDayEntity = new DiveDayEntity();


        diveDayEntity.setDiveDayId(input.getDiveDay().getDiveDayId());
        diveDayEntity.setDay(input.getDiveDay().getDay());
        diveDayEntity.setMonth(input.getDiveDay().getMonth());
        diveDayEntity.setYear(input.getDiveDay().getYear());
        diveDayEntity.setSite(input.getDiveDay().getSite());
        diveDayEntity.setBeginning(input.getDiveDay().getBeginning());
        diveDayEntity.setEnd(input.getDiveDay().getEnd());

        diveDayEntity.setNotas(input.getDiveDay().getNotas());

        List<FishingEntity> fishingEntityList = new ArrayList<>();
        for (Fishing item : input.getDiveDay().getFishingList()) {
            FishingEntity fishingEntity = new FishingEntity();
            fishingEntity.setApuntes(item.getApuntes());
            fishingEntity.setName(item.getName());
            fishingEntity.setPescado(item.isPescado());
            fishingEntity.setWeight(item.getWeight());
            fishingEntityList.add(fishingEntity);
        }

        diveDayEntity.setFishingEntityList(fishingEntityList);
        return diveDayEntity;
    }

    @Override
    public InCreateDiveDay fromRequestToCore(DailyDivingRequest input) {
        InCreateDiveDay res = new InCreateDiveDay();
        DiveDay diveDay = new DiveDay();
        diveDay.setDay(input.getDay());
        diveDay.setBeginning(input.getBeginning());
        diveDay.setEnd(input.getEnd());
        diveDay.setSite(input.getSite());
        diveDay.setNotas(input.getNotes());
        diveDay.setYear(input.getYear());
        diveDay.setMonth(input.getMonth());


        List<Fishing> fishingList = new ArrayList<>();
        Fishing fishing = null;
        for (FishingRequest item : input.getFishingRequestList()){
            fishing = new Fishing();
            fishing.setPescado(item.isCath());
            fishing.setName(item.getName());
            fishing.setWeight(item.getWeight());
            fishing.setApuntes(item.getNotes());
            fishingList.add(fishing);
        }
        diveDay.setFishingList(fishingList);

        res.setDiveDay(diveDay);
        return res;
    }

    @Override
    public OutCreateDiveDay fromCoreToOutCore(InCreateDiveDay input) {
        OutCreateDiveDay res = new OutCreateDiveDay();
        DiveDay diveDay = new DiveDay();

        diveDay.setDay(input.getDiveDay().getDay());
        diveDay.setBeginning(input.getDiveDay().getBeginning());
        diveDay.setEnd(input.getDiveDay().getEnd());
        diveDay.setSite(input.getDiveDay().getSite());
        diveDay.setNotas(input.getDiveDay().getNotas());
        diveDay.setYear(input.getDiveDay().getYear());
        diveDay.setMonth(input.getDiveDay().getMonth());

        res.setDiveDay(diveDay);
        return res;
    }

    @Override
    public DiveDayEntity fromUpdateCoreToEntity(InUpdatedDiveDay input) {
        DiveDayEntity diveDayEntity = new DiveDayEntity();


        diveDayEntity.setDiveDayId(input.getDiveDayNew().getDiveDayId());
        diveDayEntity.setDay(input.getDiveDayNew().getDay());
        diveDayEntity.setMonth(input.getDiveDayNew().getMonth());
        diveDayEntity.setYear(input.getDiveDayNew().getYear());
        diveDayEntity.setSite(input.getDiveDayNew().getSite());
        diveDayEntity.setBeginning(input.getDiveDayNew().getBeginning());
        diveDayEntity.setEnd(input.getDiveDayNew().getEnd());

        diveDayEntity.setNotas(input.getDiveDayNew().getNotas());

        List<FishingEntity> fishingEntityList = new ArrayList<>();
        for (Fishing item : input.getDiveDayNew().getFishingList()) {
            FishingEntity fishingEntity = new FishingEntity();
            fishingEntity.setApuntes(item.getApuntes());
            fishingEntity.setName(item.getName());
            fishingEntity.setPescado(item.isPescado());
            fishingEntity.setWeight(item.getWeight());
            fishingEntityList.add(fishingEntity);
        }

        diveDayEntity.setFishingEntityList(fishingEntityList);
        return diveDayEntity;
    }

}
