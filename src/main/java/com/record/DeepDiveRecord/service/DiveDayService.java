package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.dto.request.DailyDivingRequest;
import com.record.DeepDiveRecord.dto.request.FishingRequest;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiveDayService {

    @Autowired
    DiveDayRepository diveDayRepository;

    public DiveDayEntity createDailyDiving(DailyDivingRequest dailyDivingRequest){
        DiveDayEntity diveDayEntity = new DiveDayEntity();

        diveDayEntity.setDay(dailyDivingRequest.getDay());
        diveDayEntity.setMonth(dailyDivingRequest.getMonth());
        diveDayEntity.setYear(dailyDivingRequest.getYear());
        diveDayEntity.setSite(dailyDivingRequest.getSite());
        diveDayEntity.setEnd(dailyDivingRequest.getEndTime());
        diveDayEntity.setBeginning(dailyDivingRequest.getStartTime());
        diveDayEntity.setNotas(dailyDivingRequest.getNotas());

        List<FishingEntity> fishingEntityList = new ArrayList<>();
        for (FishingRequest item : dailyDivingRequest.getFishingRequestList()) {
            FishingEntity fishingEntity = new FishingEntity();
            fishingEntity.setApuntes(item.getApuntes());
            fishingEntity.setName(item.getName());
            fishingEntity.setPescado(item.isPescado());
            fishingEntity.setDiveDay(diveDayEntity); // Establecer la relación bidireccional
            fishingEntityList.add(fishingEntity);
        }

        diveDayEntity.setFishingEntityList(fishingEntityList);

        // Guardar el DiveDayEntity, esto debe hacerse dentro de una transacción
        DiveDayEntity savedDiveDay = diveDayRepository.save(diveDayEntity);

        return savedDiveDay;
    }
}