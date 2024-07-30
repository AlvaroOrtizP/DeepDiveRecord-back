package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.api.domain.diveday.creatediveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.*;
import com.record.DeepDiveRecord.api.domain.geograficlocation.GeographicalLocationResponse;
import com.record.DeepDiveRecord.api.domain.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.controller.DiveDayController;
import com.record.DeepDiveRecord.entity.*;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.mapper.GeograficLocationMapper;
import com.record.DeepDiveRecord.mapper.TideTableMapper;
import com.record.DeepDiveRecord.mapper.WindConditionsMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import com.record.DeepDiveRecord.repository.FishingRepository;
import com.record.DeepDiveRecord.repository.GeographicalLocationRepository;
import com.record.DeepDiveRecord.repository.TideTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiveDayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayController.class);
    DiveDayRepository diveDayRepository;
    GeographicalLocationRepository geographicalLocationRepository;
    TideTableRepository tideTableRepository;
    WindConditionsService windConditionsService;
    TideTableMapper tideTableMapper;
    GeograficLocationMapper geograficLocationMapper;
    DiveDayMapper diveDayMapper;
    WindConditionsMapper windConditionsMapper;

    public DiveDayService(
            DiveDayRepository diveDayRepository,
            GeographicalLocationRepository geographicalLocationRepository,
            TideTableRepository tideTableRepository,
            WindConditionsService windConditionsService,
            TideTableMapper tideTableMapper,
            GeograficLocationMapper geograficLocationMapper,
            DiveDayMapper diveDayMapper,
            WindConditionsMapper windConditionsMapper) {
        this.diveDayRepository = diveDayRepository;
        this.geographicalLocationRepository = geographicalLocationRepository;
        this.tideTableRepository = tideTableRepository;
        this.windConditionsService = windConditionsService;
        this.tideTableMapper = tideTableMapper;
        this.geograficLocationMapper = geograficLocationMapper;
        this.diveDayMapper = diveDayMapper;
        this.windConditionsMapper = windConditionsMapper;
    }

    public Integer createDiveDay(InCreateDailyDiving inCreateDailyDiving) {
        // Buscar el lugar geográfico basado en el ID
        GeographicalLocationEntity location = geographicalLocationRepository.findById(inCreateDailyDiving.getIdGeograficLocation())
                .orElseThrow(() -> new RuntimeException("GeographicalLocation not found"));
        //TODO filtro para comparar que el id de windwuru es correcto

        DiveDayEntity diveDayEntity = diveDayMapper.entityFromResponse(inCreateDailyDiving);
        diveDayEntity.setSite(location.getIdWindwuru());
        diveDayEntity.setGeographicalLocation(location);


        // Guardar el DiveDay en la base de datos
        DiveDayEntity savedDiveDay = diveDayRepository.save(diveDayEntity);

        // Retornar el ID del nuevo DiveDay
        return savedDiveDay.getDiveDayId();
    }

    public DiveDayResponse findDiveDayById(Integer id) {
        LOGGER.info("Metodo findDiveDayById incio");

        Optional<DiveDayEntity> optionalDiveDayEntity = diveDayRepository.findById(id);
        if (optionalDiveDayEntity.isEmpty()) {
            return null;
        }


        DiveDayEntity diveDayEntity = optionalDiveDayEntity.get();
        LOGGER.info("Obtiene el diveDayEntity {}", diveDayEntity.getDiveDayId());
        DiveDayResponse res = diveDayMapper.responseFromEntity(diveDayEntity);
        res.setGeographicalLocationResponse(geograficLocationMapper.responseFromDiveDayEntity(diveDayEntity));
        Optional<TideTableEntity> optionalTideTable = tideTableRepository.findById(tideTableMapper.entityIdFromDiveDayEntity(diveDayEntity));


        if (optionalTideTable.isEmpty()) {
            LOGGER.info("No se encuentra: ");
            res.setTideTableResponse(new TideTableResponse());
        } else {
            res.setTideTableResponse(tideTableMapper.responseFromEntity(optionalTideTable.get()));
        }

        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsService.getDeepDiveDataByDays(windConditionsMapper.fromDiveDayEntity(diveDayEntity));
        List<WindConditionResponse> windConditionList = new ArrayList<>();
        for (WindConditionsEntity item : windConditionsEntityPage.getContent()) {
            windConditionList.add(windConditionsMapper.responseFromEntity(item));
        }
        res.setWindConditionsList(windConditionList);


        List<FishingResponse> fishingList = new ArrayList<>();

        for (FishingEntity item : diveDayEntity.getFishingEntities()) {

            FishingResponse fishingResponse = new FishingResponse();
            fishingResponse.setId(item.getId());
            fishingResponse.setCaught(item.isCaught());
            fishingResponse.setWeight(item.getWeight());
            fishingResponse.setNotes(item.getNotes());
            fishingResponse.setLatG(item.getLatG());
            fishingResponse.setLongG(item.getLongG());
            fishingResponse.setName(item.getGeographicalLocation().getName());
            fishingResponse.setSite(item.getGeographicalLocation().getSite());
            fishingResponse.setNameFish(item.getFish().getName());
            LOGGER.info("fishingResponse " + fishingResponse);
            fishingList.add(fishingResponse);
        }

        res.setFishingList(fishingList);

        return res;
    }


}
