package com.record.DeepDiveRecord.api.domain.windconditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InDeepDiveLogger {
    private String idWindwuru; //Identificador de windwuru
    private String idAemet; //Identificador de aemet
    private String lugar; //Lugar de playa donde se quiere mirar
}
