package com.record.DeepDiveRecord.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FishingRequest {
    private String name;
    private double weight;//peso
    private String notes;//notas
    private boolean cath;//He pescado
}
