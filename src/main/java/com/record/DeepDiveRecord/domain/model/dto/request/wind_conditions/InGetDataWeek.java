package com.record.DeepDiveRecord.domain.model.dto.request.wind_conditions;

import com.record.DeepDiveRecord.domain.model.dto.request.dive_day.InCreateDailyDiving;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InGetDataWeek {

    private Integer page;
    private Integer size;
    private String site;
    private Integer fromYear;
    private Integer fromMonth;
    private Integer fromDay;
    private Integer toYear;
    private Integer toMonth;
    private Integer toDay;

    public static boolean comprobarDatosCreateDiveDay(InGetDataWeek input) {
        if (input.getPage() == null || input.getPage() < 0){
            return false;
        }
        if (input.getSize() == null || input.getSize() < 0){
            return false;
        }
        if (input.getSite() == null || input.getSite().isBlank()){
            return false;
        }
        if (input.getFromYear() == null || input.getFromYear() < 0){
            return false;
        }
        if (input.getFromMonth() == null || input.getFromMonth() < 0 || input.getFromMonth()>12){
            return false;
        }
        if (input.getFromDay() == null || input.getFromDay() < 0 || input.getFromDay()>31){
            return false;
        }
        if (input.getToYear() == null || input.getToYear() < 0){
            return false;
        }
        if (input.getToMonth() == null || input.getToMonth() < 0 || input.getToMonth()>12){
            return false;
        }
        if (input.getToDay() == null || input.getToDay() < 0 || input.getToDay()>31){
            return false;
        }
        return true;
    }
}