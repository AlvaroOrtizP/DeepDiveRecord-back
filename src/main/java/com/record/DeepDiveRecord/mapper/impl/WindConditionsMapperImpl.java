package com.record.DeepDiveRecord.mapper.impl;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.windconditions.InDataDeleteWC;
import com.record.DeepDiveRecord.core.model.windconditions.OutDailyStatistics;
import com.record.DeepDiveRecord.core.model.windconditions.OutDeteleWindConditions;
import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.entity.WindConditionsId;
import com.record.DeepDiveRecord.mapper.WindConditionsMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@Component
public class WindConditionsMapperImpl implements WindConditionsMapper {
    private final static DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public WindConditionsEntity getWindConditionsFromJson(Map.Entry<String, Map<String, String>> entry, String yearFile, String monthFile, String dayFile) throws ParseException {
        WindConditionsEntity windConditionsEntity = new WindConditionsEntity();
        String timeString = entry.getKey().replaceAll("\"", "").trim();//timeString  = 7. 13h

        //CALCULATE DATE
        // Dividir la cadena en dos partes usando el punto como delimitador
        String[] partes = timeString.split("\\.");// 7. 13h = 7

        // La parte antes del punto
        String parteAntes = partes[0].trim(); // Eliminar espacios en blanco alrededor

        // La parte después del punto y sin la 'h'
        String parteDespues = partes[1].replaceAll("h", "").trim(); // 7. 13h = 13


        Calendar calendario = getDate(Integer.parseInt(parteAntes), Integer.parseInt(dayFile), yearFile, monthFile, dayFile);


        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int dayOfYear = calendario.get(Calendar.DAY_OF_MONTH);

        WindConditionsId entityId = new WindConditionsId();
        entityId.setYear(String.valueOf(year));
        entityId.setMonth(String.valueOf(month+1));
        entityId.setDay(String.valueOf(dayOfYear));
        entityId.setTime(parteDespues);
        windConditionsEntity.setId(entityId);


        Map<String, String> conditions = entry.getValue();
        windConditionsEntity.setWind(Integer.parseInt(conditions.get("viento")));
        windConditionsEntity.setGustsOfWind(Double.parseDouble(conditions.get("rafagas")));
        windConditionsEntity.setWavePeriod(Integer.parseInt(conditions.get("periodo_olas")));
        windConditionsEntity.setEarthTemperature(Integer.parseInt(conditions.get("temperatura_tierra")));
        return windConditionsEntity;
    }

    @Override
    public OutDailyStatistics mapToOutDailyStatistics(WindConditionsEntity entity) {
        OutDailyStatistics outDailyStatistics = new OutDailyStatistics();
        outDailyStatistics.setYear(entity.getId().getYear());
        outDailyStatistics.setMonth(entity.getId().getMonth());
        outDailyStatistics.setDay(entity.getId().getDay());
        outDailyStatistics.setSite(entity.getId().getSite());
        outDailyStatistics.setTimeOfDay(entity.getId().getTime());
        outDailyStatistics.setWind(entity.getWind());
        outDailyStatistics.setWindDirection(entity.getWindDirection());
        outDailyStatistics.setGustsOfWind(entity.getGustsOfWind());
        outDailyStatistics.setWaveHeight(String.valueOf(entity.getWaveHeight()));
        outDailyStatistics.setWavePeriod(entity.getWavePeriod());
        outDailyStatistics.setEarthTemperature(entity.getEarthTemperature());
        outDailyStatistics.setWaterTtermperature(String.valueOf(entity.getWaterTemperature()));
        outDailyStatistics.setF1(entity.getCodeCondition());
        outDailyStatistics.setDescripcion1(String.valueOf(entity.getConditionDescription()));
        // Puedes mapear otros campos aquí
        return outDailyStatistics;
    }

    @Override
    public Page<OutDailyStatistics> mapToOutDailyStatisticsPage(Page<WindConditionsEntity> page) {
        List<OutDailyStatistics> outDailyStatisticsList = page.getContent().stream()
                .map(this::mapToOutDailyStatistics)
                .collect(Collectors.toList());
        return new PageImpl<>(outDailyStatisticsList, page.getPageable(), page.getTotalElements());
    }

    @Override
    public WindConditionsId mapToWindConditionsIdFromInDataDeleteWC(InDataDeleteWC in) {
        WindConditionsId out = new WindConditionsId();

        out.setYear(in.getFromYear());
        out.setMonth(in.getFromMonth());
        out.setDay(in.getFromDay());
        out.setSite(in.getSite());
        out.setTime(in.getFromTime());

        return out;
    }

    @Override
    public OutDeteleWindConditions mapToOutDeteleWindConditionsFromInDataDeleteWC(InDataDeleteWC in, boolean comprobante, String error) {
        OutDeteleWindConditions out = new OutDeteleWindConditions();

        out.setYear(in.getFromYear());
        out.setMonth(in.getFromMonth());
        out.setDay(in.getFromDay());
        out.setSite(in.getSite());
        out.setTime(in.getFromTime());
        Checker checker = new Checker(comprobante, error);
        out.setChecker(checker);
        return out;
    }

    private Calendar getDate(int diaJson, int dayFichero, String yearFile, String monthFile, String dayFile) throws ParseException {
        Calendar calendario = new GregorianCalendar();
        Date fecha = formateador.parse(dayFile + "/" + monthFile + "/" + yearFile);
        calendario.setTime(fecha);
        if (diaJson > dayFichero) {
            int aumentadorDias = diaJson - dayFichero;
            calendario.add(Calendar.DATE, aumentadorDias);
        }
        return calendario;
    }
}
