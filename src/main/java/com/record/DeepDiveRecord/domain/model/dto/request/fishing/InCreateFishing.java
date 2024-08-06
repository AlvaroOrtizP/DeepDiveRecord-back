package com.record.DeepDiveRecord.domain.model.dto.request.fishing;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InCreateFishing {
    private Integer fishId;
    private boolean caught;
    private String name;
    private String site;
    private String notes;
    private BigDecimal weight;
    private double latG;
    private double longG;
    private Integer idDiveDay;
}