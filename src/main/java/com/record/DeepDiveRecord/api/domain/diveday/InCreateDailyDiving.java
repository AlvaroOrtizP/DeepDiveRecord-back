package com.record.DeepDiveRecord.api.domain.diveday;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InCreateDailyDiving {
    //Datos del dia de buceo para dive_day
    private String day; //varchar (5)
    private String beginning; //varchar (5)
    private String end; //varchar (5)
    private String site; //varchar (50)
    private String notes; //varchar (255)
    private String year; //varchar (4)
    private String month; //varchar (2)

    //Lista de pesca del dia de buceo
    private List<InFishing> fishingList;

}
