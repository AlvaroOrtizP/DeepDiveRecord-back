package com.record.DeepDiveRecord.domain.model.dto.request.fishing.edit;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InEditFishing {
    private Integer fishingId;
    private Integer fishId;
    private boolean caught;
    private String name;
    private String site;
    private String notes;
    private BigDecimal weight;
    private double latG;
    private double longG;
    private Integer idDiveDay;
    private String sightingTime;
}
