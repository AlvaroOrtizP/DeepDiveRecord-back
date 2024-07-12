package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.api.domain.diveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.api.domain.diveday.InFishing;
import com.record.DeepDiveRecord.core.model.common.DiveDay;
import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.InUpdatedDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishEntity;
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
        diveDayEntity.setNotes(input.getDiveDay().getNotes());
        return diveDayEntity;
    }

    @Override
    public InCreateDiveDay fromRequestToCore(InCreateDailyDiving input) {
        InCreateDiveDay res = new InCreateDiveDay();
        DiveDay diveDay = new DiveDay();
        diveDay.setDay(input.getDay());
        diveDay.setBeginning(input.getBeginning());
        diveDay.setEnd(input.getEnd());
        diveDay.setSite(input.getSite());
        diveDay.setNotes(input.getNotes());
        diveDay.setYear(input.getYear());
        diveDay.setMonth(input.getMonth());


        List<Fishing> fishingList = new ArrayList<>();
        Fishing fishing = null;
        for (InFishing item : input.getFishingList()) {
            fishing = new Fishing();
            fishing.setFishId(item.getFishId());
            fishing.setCaught(item.isCaught());
            fishing.setWeight(item.getWeight());
            fishing.setNotes(item.getNotes());
            fishingList.add(fishing);
        }
        diveDay.setFishingList(fishingList);

        res.setDiveDay(diveDay);
        return res;
    }

    @Override
    public OutCreateDiveDay fromCoreToOutModelCore(InCreateDiveDay input) {
        OutCreateDiveDay res = new OutCreateDiveDay();
        DiveDay diveDay = new DiveDay();
        diveDay.setDiveDayId(input.getDiveDay().getDiveDayId());
        diveDay.setDay(input.getDiveDay().getDay());
        diveDay.setBeginning(input.getDiveDay().getBeginning());
        diveDay.setEnd(input.getDiveDay().getEnd());
        diveDay.setSite(input.getDiveDay().getSite());
        diveDay.setNotes(input.getDiveDay().getNotes());
        diveDay.setYear(input.getDiveDay().getYear());
        diveDay.setMonth(input.getDiveDay().getMonth());

        res.setDiveDay(diveDay);
        return res;
    }

    @Override
    public com.record.DeepDiveRecord.api.domain.diveday.OutCreateDiveDay mapFromOutCreateModelToOutCreateApi(OutCreateDiveDay input) {
        com.record.DeepDiveRecord.api.domain.diveday.OutCreateDiveDay res = new com.record.DeepDiveRecord.api.domain.diveday.OutCreateDiveDay();

        res.setDiveDayId(input.getDiveDay().getDiveDayId());
        res.setDay(input.getDiveDay().getDay());
        res.setBeginning(input.getDiveDay().getBeginning());
        res.setEnd(input.getDiveDay().getEnd());
        res.setSite(input.getDiveDay().getSite());
        res.setNotes(input.getDiveDay().getNotes());
        res.setYear(input.getDiveDay().getYear());
        res.setMonth(input.getDiveDay().getMonth());

        res.setChecker(input.getChecker().isChecker());
        res.setMessage(input.getChecker().getMessage());

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

        diveDayEntity.setNotes(input.getDiveDayNew().getNotes());

        List<FishingEntity> fishingEntityList = new ArrayList<>();
        for (Fishing item : input.getDiveDayNew().getFishingList()) {
            FishingEntity fishingEntity = new FishingEntity();
            fishingEntity.setNotes(item.getNotes());
            fishingEntity.setCaught(item.isCaught());
            fishingEntity.setWeight(item.getWeight());
            fishingEntityList.add(fishingEntity);
        }

        //diveDayEntity.setFishingEntityList(fishingEntityList);
        return diveDayEntity;
    }


}
