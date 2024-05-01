package com.record.DeepDiveRecord.core.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TideRegister {
    //https://www.puertosantander.es/es/tabla-de-mareas
    private String day;
    private String month;
    private String year;
    private String moon;
    private String site;

    private LunarPhase lunarPhase;//faseLunar
    private String morningHighTideTime;//pleamarManana
    private double morningHighTideHeight;//alturaPleamarManana
    private String eveningHighTideTime;//pleamarTarde
    private double eveningHighTideHeight;//alturaPleamarTarde
    private int coefficient0H;//coeficiente0H
    private int coefficient12H;//coeficiente12H
    private String morningLowTideTime;//bajamarManana
    private double morningLowTideHeight;//alturaBajamarManana
    private String eveningLowTideTime;//bajamarTarde
    private double eveningLowTideHeight;//alturaBajamarTarde
}
