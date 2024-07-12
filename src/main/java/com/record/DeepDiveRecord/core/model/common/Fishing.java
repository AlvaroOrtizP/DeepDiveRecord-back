package com.record.DeepDiveRecord.core.model.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fishing {
    private int fishId;
    private String notes;
    private Double weight;
    private boolean caught;
}
