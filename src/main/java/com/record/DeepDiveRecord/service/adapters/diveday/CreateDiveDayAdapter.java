package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.common.Fishing;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.CreateDiveDayPort;
import com.record.DeepDiveRecord.core.ports.fishing.CreateFishingDayPort;
import com.record.DeepDiveRecord.dto.request.FishingRequest;
import com.record.DeepDiveRecord.entity.DiveDayAndFishing;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.FishEntity;
import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import com.record.DeepDiveRecord.repository.FishRepository;
import com.record.DeepDiveRecord.repository.FishingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateDiveDayAdapter implements CreateDiveDayPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateDiveDayAdapter.class);

    DiveDayMapper mapper;
    FishingMapper fishingMapper;
    FishRepository fishRepository;
    FishingRepository fishingRepository;
    DiveDayRepository diveDayRepository;
    CreateFishingDayPort createFishingDayPort;

    public CreateDiveDayAdapter(DiveDayMapper mapper, DiveDayRepository diveDayRepository, FishingMapper fishingMapper,
                                CreateFishingDayPort createFishingDayPort, FishRepository fishRepository, FishingRepository fishingRepository) {
        {
            this.mapper = mapper;
            this.diveDayRepository = diveDayRepository;
            this.fishingMapper = fishingMapper;
            this.createFishingDayPort = createFishingDayPort;
            this.fishRepository = fishRepository;
            this.fishingRepository = fishingRepository;
        }

    }

    //Quitar de la entidades las relaciones para hacerlo a mano
    @Override
    @Transactional
    public OutCreateDiveDay createDiveDay(InCreateDiveDay input) {
        /*LOGGER.info("Comienza el metodo createDiveDay con input: {}", input);

        try {
            DiveDayEntity diveDayEntity = mapper.fromCreateCoreToEntity(input);
            LOGGER.info("Se mappea el input a la entidad {}", diveDayEntity);

           /* for (FishingEntity item : diveDayEntity.getFishingEntityList()) {
                Fishing fishing = fishingMapper.fromEntityToCore(item);
                createFishingDayPort.createFishingDay(fishing);
            }

            diveDayRepository.save(diveDayEntity);

            LOGGER.info("Finalizado el save de los datos con exito");
            return new OutCreateDiveDay(mapper.fromCoreToOutModelCore(input).getDiveDay(), new Checker(true, "CORRECT"));
        } catch (Exception e) {
            LOGGER.error("Finalizado el save de los datos con errores: {}", e.getMessage());
            return new OutCreateDiveDay(mapper.fromCoreToOutModelCore(input).getDiveDay(), new Checker(false, "ERROR " + e.getMessage()));
        }*/
        DiveDayEntity diveDay = new DiveDayEntity();
        diveDay.setDay(input.getDiveDay().getDay());
        diveDay.setBeginning(input.getDiveDay().getBeginning());
        diveDay.setEnd(input.getDiveDay().getEnd());
        diveDay.setSite(input.getDiveDay().getSite());
        diveDay.setNotes(input.getDiveDay().getNotes());
        diveDay.setYear(input.getDiveDay().getYear());
        diveDay.setMonth(input.getDiveDay().getMonth());

        List<DiveDayAndFishing> diveDayAndFishingList = new ArrayList<>();
        for (Fishing fishingRequest : input.getDiveDay().getFishingList()) {

            FishingEntity fishingEntity = new FishingEntity();
            fishingEntity.setFish(fishRepository.findById(fishingRequest.getFishId()).orElseThrow());
            fishingEntity.setId(fishingRequest.getFishId());
            fishingEntity.setNotes(fishingRequest.getNotes());
            fishingEntity.setCaught(fishingRequest.isCaught());
            fishingEntity.setWeight(fishingRequest.getWeight());

            FishingEntity savedFishing = fishingRepository.save(fishingEntity);

            DiveDayAndFishing diveDayAndFishing = new DiveDayAndFishing();
            diveDayAndFishing.setFishing(savedFishing);
            diveDayAndFishing.setDiveDay(diveDay);

            diveDayAndFishingList.add(diveDayAndFishing);
        }

        diveDay.setDiveDayAndFishingList(diveDayAndFishingList);

        diveDayRepository.save(diveDay);

        return null;
    }
}
