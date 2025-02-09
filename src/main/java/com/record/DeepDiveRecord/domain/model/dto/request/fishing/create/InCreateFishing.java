package com.record.DeepDiveRecord.domain.model.dto.request.fishing.create;

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
    private Double latG;
    private Double longG;
    private Integer idDiveDay;
    private String sightingTime;
}