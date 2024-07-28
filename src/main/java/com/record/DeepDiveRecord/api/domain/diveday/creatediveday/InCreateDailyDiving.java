package com.record.DeepDiveRecord.api.domain.diveday.creatediveday;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InCreateDailyDiving {
    private String day;
    private String month;
    private String year;
    private String beginning;
    private String end;
    private String site; //El nombre del pueblo
    private String name; //El nombre de la zona
    private String notes;
    private Integer assessment;
    private Integer idGeograficLocation;
    private Integer jellyfish;
    private Integer visibility;
    private Integer seaBackground;
    private Integer fishGrass;
    private Integer presencePlastic;
}
