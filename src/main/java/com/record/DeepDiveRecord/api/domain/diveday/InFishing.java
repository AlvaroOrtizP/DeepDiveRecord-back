package com.record.DeepDiveRecord.api.domain.diveday;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InFishing {
    Integer fishId; // identificador del pez (tabla fish)
    private String notes; //varchar(255) apuntes/notas
    private Double weight; //decimal(5,2)  peso del pez
    private boolean caught; //Indicador de si fue pescado o no (true = 1, false = 0)
}
