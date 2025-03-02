package com.record.DeepDiveRecord.application.service;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataMedia;
import com.record.DeepDiveRecord.domain.model.exception.InvalidDiveDayDataException;
import com.record.DeepDiveRecord.domain.port.DiveDayPort;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl.CommonMapperImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.impl.WindConditionsMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.record.DeepDiveRecord.application.usecase.WindConditionsUseCase;
import com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions.InGetDataWeek;
import com.record.DeepDiveRecord.domain.model.dto.response.common.Pagination;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetData;
import com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions.OutGetDataList;
import com.record.DeepDiveRecord.domain.port.WindConditionsPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.CommonMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.WindConditionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WindConditionsService implements WindConditionsUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindConditionsService.class);

    @Autowired
    WindConditionsPort windConditionsPort;
    @Autowired
    DiveDayPort diveDayPort;

    WindConditionsMapper windConditionsMapper = new WindConditionsMapperImpl();
    CommonMapper commonMapper = new CommonMapperImpl();

    @Override
    public OutGetDataList getDeepDiveDataByDays(InGetDataWeek input) {
        LOGGER.info("--------------------------------------------------------------------------------------------");
        LOGGER.info("Inicia el método getDeepDiveDataByDays con el input: {}", input);

        if(!InGetDataWeek.comprobarDatosCreateDiveDay(input)){
            throw new InvalidDiveDayDataException("Los datos para obtener las condiciones climaticas no son válidos.");
        }

        // Obtener datos de condiciones de viento
        Page<WindConditionsEntity> windConditionsEntityPage = fetchWindConditionsData(input, false);

        // Procesar datos obtenidos y preparar la respuesta
        OutGetDataList response = prepareOutGetDataList(windConditionsEntityPage);

        LOGGER.info("Finaliza el método getDeepDiveDataByDays.");
        LOGGER.info("--------------------------------------------------------------------------------------------");

        // Devolver la respuesta completa
        return response;
    }

    /**
     * Método privado para obtener los datos de condiciones de viento de la base de datos.
     */
    private Page<WindConditionsEntity> fetchWindConditionsData(InGetDataWeek input, boolean onlyImpares) {
        LOGGER.info("Obteniendo los datos de condiciones de viento para la semana especificada.");
        Page<WindConditionsEntity> windConditionsEntityPage = windConditionsPort.getDeepDiveDataByDays(
                windConditionsMapper.fromInGetDataWeekToDtoFindDeepData(input), onlyImpares
        );
        LOGGER.info("Se han obtenido {} entidades de WindConditions del repositorio.", windConditionsEntityPage.getTotalElements());
        return windConditionsEntityPage;
    }

    /**
     * Método privado para preparar la lista de resultados y la paginación en la respuesta.
     */
    private OutGetDataList prepareOutGetDataList(Page<WindConditionsEntity> windConditionsEntityPage) {
        LOGGER.info("Preparando la lista de resultados y la paginación para la respuesta.");

        OutGetDataList response = new OutGetDataList();
        List<OutGetData> outGetDataList = new ArrayList<>();
        List<OutGetDataMedia> outOutGetDataMediaList = new ArrayList<>();
        OutGetDataMedia outGetDataMedia = new OutGetDataMedia();
        int timeOfDay = 0;

        //TODO MODIFICAR EL VALOR DE categoria para que sea "destacada" o "malo"

        for (WindConditionsEntity item : windConditionsEntityPage.getContent()) {
            OutGetData outGetData = windConditionsMapper.getOutGetData(item);
            outGetDataList.add(outGetData);

            timeOfDay = item.getId().getTime()<14 ? 1 : 2;

            // si es la primera iteracion se obtienen todos los datos
            if(outGetDataMedia.getTimeOfDay() == null){
                //Si la hora es inferior a 14 es mañana y si es mayor es tarde
                outGetDataMedia = windConditionsMapper.getOutGetDataMedia(item);
            }

            // Si el momento del dia es el mismo se recalculan los datos
            else if (outGetDataMedia.getTimeOfDay() == timeOfDay) {
                obtenerCondicionesMaxMin(item, outGetDataMedia);
            }
            if (outGetDataMedia.getTimeOfDay() != timeOfDay) {
                outOutGetDataMediaList.add(outGetDataMedia);
                outGetDataMedia = windConditionsMapper.getOutGetDataMedia(item);
            }
        }
        if(!windConditionsEntityPage.isEmpty()){
            outOutGetDataMediaList.add(outGetDataMedia);
        }

        LOGGER.info("Se han mapeado {} entidades WindConditions a objetos OutGetData.", outGetDataList.size());

        // Configurar la lista de resultados y la paginación en la respuesta
        response.setOutGetDataList(outGetDataList);
        response.setOutGetDataMediaList(outOutGetDataMediaList);
        Pagination pagination = commonMapper.getPagination(windConditionsEntityPage);
        response.setPagination(pagination);

        LOGGER.info("Se ha configurado la paginación de la respuesta.");
        return response;
    }

    private static void obtenerCondicionesMaxMin(WindConditionsEntity item, OutGetDataMedia outGetDataMedia) {
        outGetDataMedia.setTimeOfDay(item.getId().getTime()<14 ? 1 : 2);
        // viento
        if(item.getWind()< outGetDataMedia.getMinWinter()){
            outGetDataMedia.setMinWinter(item.getWind());
        }
        if(item.getWind()> outGetDataMedia.getMaxWinter()){
            outGetDataMedia.setMaxWinter(item.getWind());
        }

        //rachas del viento
        if(item.getGustsOfWind()< outGetDataMedia.getMaxGustsOfWind()){
            outGetDataMedia.setMinGustsOfWind(item.getGustsOfWind());
        }
        if(item.getGustsOfWind()> outGetDataMedia.getMaxGustsOfWind()){
            outGetDataMedia.setMaxGustsOfWind(item.getGustsOfWind());
        }

        //Altura olas
        if(item.getWaveHeight()< outGetDataMedia.getMinWaveHeight()){
            outGetDataMedia.setMinWaveHeight(item.getWaveHeight());
        }
        if(item.getWaveHeight()> outGetDataMedia.getMaxWaveHeight()){
            outGetDataMedia.setMaxWaveHeight(item.getWaveHeight());
        }

        //periodo de olas
        if(item.getWavePeriod()< outGetDataMedia.getMinWavePeriod()){
            outGetDataMedia.setMinWavePeriod(item.getWavePeriod());
        }
        if(item.getWavePeriod()> outGetDataMedia.getMaxWavePeriod()){
            outGetDataMedia.setMaxWavePeriod(item.getWavePeriod());
        }

        //temperatura tierra
        if(item.getEarthTemperature()< outGetDataMedia.getMinEarthTemperature()){
            outGetDataMedia.setMinEarthTemperature(item.getEarthTemperature());
        }
        if(item.getEarthTemperature()> outGetDataMedia.getMaxEarthTemperature()){
            outGetDataMedia.setMaxEarthTemperature(item.getEarthTemperature());
        }

        //temperatura agua
        if(item.getWaterTemperature() != null ){

            if(outGetDataMedia.getMinWaterTemperature().equals("NA")){
                outGetDataMedia.setMinWaterTemperature(String.valueOf(item.getWaterTemperature()));
            }else{
                int waterTem = Integer.parseInt(outGetDataMedia.getMinWaterTemperature());
                if(item.getWaterTemperature()<waterTem){
                    outGetDataMedia.setMinWaterTemperature(String.valueOf(item.getWaterTemperature()));
                }
            }


            if(outGetDataMedia.getMaxWaterTemperature().equals("NA")){
                outGetDataMedia.setMaxWaterTemperature(String.valueOf(item.getWaterTemperature()));
            }else{
                int waterTem = Integer.parseInt(outGetDataMedia.getMaxWaterTemperature());
                if(item.getWaterTemperature()>waterTem){
                    outGetDataMedia.setMaxWaterTemperature(String.valueOf(item.getWaterTemperature()));
                }
            }
        }else{
            outGetDataMedia.setMinWaterTemperature("NA");
            outGetDataMedia.setMaxWaterTemperature("NA");
        }
    }
}
