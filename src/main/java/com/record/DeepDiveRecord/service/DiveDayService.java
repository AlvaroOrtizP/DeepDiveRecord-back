package com.record.DeepDiveRecord.service;

import com.record.DeepDiveRecord.api.domain.diveday.creatediveday.InCreateDailyDiving;
import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.DiveDayResponse;
import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.TideTableResponse;
import com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid.WindConditionResponse;
import com.record.DeepDiveRecord.api.domain.geograficlocation.GeographicalLocationResponse;
import com.record.DeepDiveRecord.controller.DiveDayController;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.entity.TideTableId;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import com.record.DeepDiveRecord.repository.GeographicalLocationRepository;
import com.record.DeepDiveRecord.repository.TideTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public DiveDayService(
            DiveDayRepository diveDayRepository,
            GeographicalLocationRepository geographicalLocationRepository,
            TideTableRepository tideTableRepository) {
        this.diveDayRepository = diveDayRepository;
        this.geographicalLocationRepository = geographicalLocationRepository;
        this.tideTableRepository = tideTableRepository;
    }

    public Integer createDiveDay(InCreateDailyDiving inCreateDailyDiving) {
        // Buscar el lugar geográfico basado en el ID
        GeographicalLocationEntity location = geographicalLocationRepository.findById(inCreateDailyDiving.getIdGeograficLocation())
                .orElseThrow(() -> new RuntimeException("GeographicalLocation not found"));
        //TODO filtro para comparar que el id de windwuru es correcto

        DiveDayEntity diveDayEntity = getDiveDayEntity(inCreateDailyDiving, location);


        // Guardar el DiveDay en la base de datos
        DiveDayEntity savedDiveDay = diveDayRepository.save(diveDayEntity);

        // Retornar el ID del nuevo DiveDay
        return savedDiveDay.getDiveDayId();
    }

    public DiveDayResponse findDiveDayById(Integer id) {
        LOGGER.info("Metodo findDiveDayById incio");
        DiveDayResponse res = new DiveDayResponse();
        Optional<DiveDayEntity> optionalDiveDayEntity = diveDayRepository.findById(id);
        if (optionalDiveDayEntity.isEmpty()) {
            return null;
        }
        DiveDayEntity diveDayEntity = optionalDiveDayEntity.get();
        LOGGER.info("Obtiene el diveDayEntity {}" + diveDayEntity.getDiveDayId());



        res.setDiveDayId(diveDayEntity.getDiveDayId());
        res.setDay(Integer.valueOf(diveDayEntity.getDay()));
        res.setMonth(Integer.valueOf(diveDayEntity.getMonth()));
        res.setYear(Integer.valueOf(diveDayEntity.getYear()));
        res.setBeginning(diveDayEntity.getBeginning());
        res.setEnd(diveDayEntity.getEnd());
        res.setSite(diveDayEntity.getSite());
        res.setValoracion(diveDayEntity.getAssessment());
        res.setNotes(diveDayEntity.getNotes());
        GeographicalLocationResponse geograficLocationResponse = new GeographicalLocationResponse();
        geograficLocationResponse.setIdWindwuru(diveDayEntity.getGeographicalLocation().getIdWindwuru());
        geograficLocationResponse.setName(diveDayEntity.getGeographicalLocation().getName());
        geograficLocationResponse.setId(diveDayEntity.getGeographicalLocation().getId());
        geograficLocationResponse.setSite(diveDayEntity.getGeographicalLocation().getSite());
        res.setGeographicalLocationResponse(geograficLocationResponse);



        TideTableResponse tideTableResponse = new TideTableResponse();
        TideTableId tideTableId = new TideTableId();
        tideTableId.setDay(diveDayEntity.getDay());
        tideTableId.setYear(diveDayEntity.getYear());
        tideTableId.setMonth(diveDayEntity.getMonth());
        tideTableId.setSite(diveDayEntity.getSite());
        Optional<TideTableEntity> optionalTideTable = tideTableRepository.findById(tideTableId);
        LOGGER.info("Se busca tideTable con identificador {}", tideTableId.toString());

        if (optionalDiveDayEntity.isEmpty()) {
            LOGGER.info("No se encuentra: ");
            res.setTideTableResponse(tideTableResponse);
        }else{
            TideTableEntity tideTableEntity = optionalTideTable.get();
            LOGGER.info("Se encuentra: " + tideTableEntity.toString());
            tideTableResponse.setDay(Integer.valueOf(tideTableEntity.getId().getDay()));
            tideTableResponse.setMonth(Integer.valueOf(tideTableEntity.getId().getMonth()));
            tideTableResponse.setYear(Integer.valueOf(tideTableEntity.getId().getYear()));
            tideTableResponse.setSite(Integer.valueOf(tideTableEntity.getId().getSite()));
            tideTableResponse.setMoonPhase(tideTableEntity.getMoonPhase());
            tideTableResponse.setCoefficient0H(tideTableEntity.getCoefficient0H());
            tideTableResponse.setCoefficient12H(tideTableEntity.getCoefficient12H());
            tideTableResponse.setMorningHighTideTime(tideTableEntity.getMorningHighTideTime());
            tideTableResponse.setMorningHighTideHeight(String.valueOf(tideTableEntity.getMorningHighTideHeight()));
            tideTableResponse.setAfternoonHighTideTime(tideTableEntity.getAfternoonHighTideTime());
            tideTableResponse.setAfternoonHighTideHeight(String.valueOf(tideTableEntity.getAfternoonHighTideHeight()));
            tideTableResponse.setMorningLowTideTime(tideTableEntity.getMorningLowTideTime());
            tideTableResponse.setMorningLowTideHeight(String.valueOf(tideTableEntity.getMorningLowTideHeight()));
            tideTableResponse.setAfternoonLowTideTime(tideTableEntity.getAfternoonLowTideTime());
            tideTableResponse.setAfternoonLowTideHeight(String.valueOf(tideTableEntity.getAfternoonLowTideHeight()));
        }


        //TODO hacer bien el windCondtion
        List<WindConditionResponse> windConditionList = new ArrayList<>();
        WindConditionResponse windConditionResponse = new WindConditionResponse();
        windConditionResponse.setYear(2024);
        windConditionResponse.setMonth(7);
        windConditionResponse.setDay(25);
        windConditionResponse.setTimeOfDay(21);
        windConditionResponse.setSite("487006");
        windConditionResponse.setWind(12);
        windConditionResponse.setWindDirection(12);
        windConditionResponse.setWindDirectionNM("N");
        windConditionResponse.setGustsOfWind(12);
        windConditionResponse.setWaveHeight("12");
        windConditionResponse.setWavePeriod(12);
        windConditionResponse.setWaveDirection("12");
        windConditionResponse.setWaveDirectionNM("NM");
        windConditionResponse.setEarthTemperature(12);
        windConditionResponse.setWaterTermperature("22");
        windConditionResponse.setConditionCode(12);
        windConditionResponse.setConditionDescription("algo");

        windConditionList.add(windConditionResponse);
        res.setWindConditionsList(windConditionList);

        res.setFishingList(new ArrayList<>());


        res.setTideTableResponse(tideTableResponse);


        return res;
    }

    private static DiveDayEntity getDiveDayEntity(InCreateDailyDiving inCreateDailyDiving, GeographicalLocationEntity location) {
        DiveDayEntity diveDayEntity = new DiveDayEntity();
        diveDayEntity.setDay(inCreateDailyDiving.getDay());
        diveDayEntity.setMonth(inCreateDailyDiving.getMonth());
        diveDayEntity.setYear(inCreateDailyDiving.getYear());
        diveDayEntity.setBeginning(inCreateDailyDiving.getBeginning());
        diveDayEntity.setEnd(inCreateDailyDiving.getEnd());
        diveDayEntity.setSite(location.getIdWindwuru());//idWindwuru
        //Name es el filtro para comprobar que existe en geografia
        diveDayEntity.setNotes(inCreateDailyDiving.getNotes());
        diveDayEntity.setAssessment(inCreateDailyDiving.getValoracion());
        diveDayEntity.setGeographicalLocation(location);
        return diveDayEntity;
    }
}
