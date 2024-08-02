package com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FishingResponse {
    private Integer id;
    private String notes;
    private boolean caught;
    private BigDecimal weight;
    private double latG;
    private double longG;
    private String name;
    private String site;
    private String nameFish;
}
