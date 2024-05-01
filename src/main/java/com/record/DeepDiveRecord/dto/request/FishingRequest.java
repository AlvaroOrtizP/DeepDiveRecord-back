package com.record.DeepDiveRecord.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FishingRequest {
    private String name;
    private double weight;//peso
    private String notes;//notas
    private boolean cath;//He pescado
}
