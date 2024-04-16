package com.record.DeepDiveRecord.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InFishing {
    private String name;
    private String weight;
    private String apuntes;
    private boolean pescado;
}
