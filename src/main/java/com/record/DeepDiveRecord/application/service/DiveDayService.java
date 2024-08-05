package com.record.DeepDiveRecord.application.service;

import com.record.DeepDiveRecord.application.usecase.DiveDayUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.diveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.DiveDayResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.FishingResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.TideTableResponse;
import com.record.DeepDiveRecord.domain.model.dto.response.diveday.WindConditionResponse;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.domain.port.TideTablePort;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.*;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DiveDayService implements DiveDayUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiveDayService.class);
    @Autowired
    DiveDayPort diveDayPort;
    @Autowired
    TideTablePort tideTablePort;
    @Autowired
    DiveDayMapper diveDayMapper;
    @Autowired
    GeograficLocationMapper geograficLocationMapper;
    @Autowired
    TideTableMapper tideTableMapper;

    @Autowired
    WindConditionsPort windConditionsPort;
    @Autowired
    WindConditionsMapper windConditionsMapper;
    @Autowired
    FishMapper fishMapper;
    @Autowired
    GeographicalLocationPort geographicalLocationPort;
    @Override
    public Integer createDiveDay(InCreateDailyDiving inCreateDailyDiving) {
        GeographicalLocationEntity location = geographicalLocationPort.findById(inCreateDailyDiving.getIdGeograficLocation())
                .orElseThrow(() -> new EntityNotFoundException("GeografiEntity not found with id " + inCreateDailyDiving.getIdGeograficLocation()));
        //TODO filtro para comparar que el id de windwuru es correcto

        DiveDayEntity diveDayEntity = diveDayMapper.entityFromResponse(inCreateDailyDiving);
        diveDayEntity.setSite(location.getIdWindwuru());
        diveDayEntity.setGeographicalLocation(location);


        // Guardar el DiveDay en la base de datos
        DiveDayEntity savedDiveDay = diveDayPort.save(diveDayEntity);

        // Retornar el ID del nuevo DiveDay
        return savedDiveDay.getDiveDayId();
    }

    @Override
    public DiveDayResponse findDiveDayById(Integer id) {

        DiveDayEntity diveDayEntity = diveDayPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DiveDayEntity not found with id " + id));


        LOGGER.info("Obtiene el diveDayEntity {}", diveDayEntity.getDiveDayId());
        DiveDayResponse res = diveDayMapper.responseFromEntity(diveDayEntity);
        res.setGeographicalLocationResponse(geograficLocationMapper.responseFromDiveDayEntity(diveDayEntity));


        Optional<TideTableEntity> optionalTideTable = tideTablePort.findById(tideTableMapper.entityIdFromDiveDayEntity(diveDayEntity));

        if (optionalTideTable.isEmpty()) {
            LOGGER.info("No se encuentra: ");
            res.setTideTableResponse(new TideTableResponse());
        } else {
            res.setTideTableResponse(tideTableMapper.responseFromEntity(optionalTideTable.get()));
        }
        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsPort.getDeepDiveDataByDays(windConditionsMapper.fromDiveDayEntity(diveDayEntity));
        List<WindConditionResponse> windConditionList = new ArrayList<>();
        for (WindConditionsEntity item : windConditionsEntityPage.getContent()) {
            windConditionList.add(windConditionsMapper.responseFromEntity(item));
        }
        res.setWindConditionsList(windConditionList);


        List<FishingResponse> fishingList = new ArrayList<>();

        for (FishingEntity item : diveDayEntity.getFishingEntities()) {
            fishingList.add(fishMapper.responseFromEntity(item));
        }

        res.setFishingList(fishingList);

        return res;
    }
}
