package com.record.DeepDiveRecord.mapper;

import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.entity.WindConditionsId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class WindConditionsMapper {
    private final static DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @param entry
     * @param yearFile  2024
     * @param monthFile 03
     * @param dayFile   10
     * @return
     */
    public static final WindConditionsEntity getWindConditionsFromJson(Map.Entry<String, Map<String, String>> entry, String yearFile, String monthFile, String dayFile) {
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
    private static Calendar getDate(int diaJson, int dayFichero, String yearFile, String monthFile, String dayFile){
        // creamos un calendario
        Calendar calendario = new GregorianCalendar();
        try {
            // Obtenemos la fecha de creacion del fichero
            Date fecha = formateador.parse(dayFile + "/" + monthFile + "/" + yearFile);



            //hacemos calculos sobre el calendario
            calendario.setTime(fecha);
            //Comprobamos si el dia (diaJson) es inferior al dia del nombre del fichero (dayFichero)
            //(10) 10. 15h <>> datos_2024_03_10.json (10)
            if (diaJson > dayFichero) {
                int aumentadorDias = diaJson - dayFichero;
                calendario.add(Calendar.DATE, aumentadorDias);
            }
            // Obtener los valores actualizados del calendario



        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendario;
    }
}
