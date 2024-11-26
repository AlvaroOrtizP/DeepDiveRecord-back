package com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl;

import com.record.DeepDiveRecord.domain.model.dto.request.fishing.InCreateFishing;
import com.record.DeepDiveRecord.domain.model.dto.response.fishing.FishingDetails;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.FishingMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.UtilityMapper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FishingMapperImpl implements FishingMapper {
    @Autowired
    private UtilityMapper utilityMapper;
    @Override
    public FishingEntity fromRequestToEntity(InCreateFishing input) {
        FishingEntity res = new FishingEntity();
        res.setCaught(input.isCaught());
        res.setNotes(input.getNotes());
        res.setWeight(input.getWeight());
        FishEntity fish = new FishEntity();
        fish.setId(input.getFishId());
        res.setFish(fish);
        res.setLatG(input.getLatG());
        res.setLongG(input.getLongG());
        res.setSightingTime(input.getSightingTime());
        DiveDayEntity diveDay = new DiveDayEntity();
        diveDay.setDiveDayId(input.getIdDiveDay());
        res.setDiveDay(diveDay);
        return res;
    }

    @Override
    public FishingDetails fromEntityToResponse(FishingEntity input) {
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

    @Override
    public FishingEntity mapRowToFishingEntity(Row row) {
        FishingEntity entity = new FishingEntity();
        entity.setId(utilityMapper.getCellValueAsInteger(row.getCell(0)));
        FishEntity fish = new FishEntity();
        fish.setId(utilityMapper.getCellValueAsInteger(row.getCell(1)));
        entity.setFish(fish);

        entity.setNotes(utilityMapper.getCellValueAsString(row.getCell(2)));
        entity.setCaught(utilityMapper.getCellValueAsBoolean(row.getCell(3)));
        entity.setWeight(BigDecimal.valueOf(utilityMapper.getCellValueAsInteger(row.getCell(4))));

        entity.setLatG(utilityMapper.getCellValueAsInteger(row.getCell(5)));
        entity.setLongG(utilityMapper.getCellValueAsInteger(row.getCell(6)));

        GeographicalLocationEntity geo = new GeographicalLocationEntity();
        geo.setId(utilityMapper.getCellValueAsInteger(row.getCell(5)));
        entity.setGeographicalLocation(geo);

        entity.setSightingTime(utilityMapper.getCellValueAsString(row.getCell(8)));
        return entity;
    }
}
