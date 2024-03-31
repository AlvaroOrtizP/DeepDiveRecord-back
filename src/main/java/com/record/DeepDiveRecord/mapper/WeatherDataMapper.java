package com.record.DeepDiveRecord.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.record.DeepDiveRecord.entity.WeatherDataEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherDataMapper {

    public static final List<WeatherDataEntity> getWeatherDataFromJson(List<Object> in) {
        List<WeatherDataEntity> weatherDataEntities = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        for (Object obj : in) {
            try {
                Map<String, Object> dataMap = objectMapper.convertValue(obj, Map.class);

                WeatherDataEntity weatherDataEntity = new WeatherDataEntity();
                weatherDataEntity.setDate((String) dataMap.get("fecha"));
                weatherDataEntity.setWaterTemperature((String) dataMap.get("t_agua"));

                Map<String, Object> estadoCieloMap = (Map<String, Object>) dataMap.get("estado_cielo");
                if (estadoCieloMap != null) {
                    weatherDataEntity.setF1((String) estadoCieloMap.get("f1"));
                    weatherDataEntity.setDescripcion1((String) estadoCieloMap.get("descripcion1"));
                    weatherDataEntity.setF2((String) estadoCieloMap.get("f2"));
                    weatherDataEntity.setDescripcion2((String) estadoCieloMap.get("descripcion2"));
                }

                weatherDataEntities.add(weatherDataEntity);
            } catch (IllegalArgumentException e) {
                // Manejar la excepción si ocurre algún problema al convertir los datos
                e.printStackTrace();
            }
        }

        return weatherDataEntities;
    }

}