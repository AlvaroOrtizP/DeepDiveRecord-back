package com.record.DeepDiveRecord.core.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fishing {
    private int id;
    private String name;
    private double weight;
    private String apuntes;
    private boolean pescado;
}
