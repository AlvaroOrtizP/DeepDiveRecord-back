package com.record.DeepDiveRecord.api.domain.fish;

import lombok.*;

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