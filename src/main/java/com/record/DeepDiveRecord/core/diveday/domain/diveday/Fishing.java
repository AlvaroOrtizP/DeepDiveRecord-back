package com.record.DeepDiveRecord.core.diveday.domain.diveday;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fishing {
    private String name;
    private String weight;
    private String apuntes;
    private boolean pescado;
}
