package com.record.DeepDiveRecord.domain.model.dto.response.fishing.create;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FishingDetails {

    private Integer id;
    private String notes;
    private boolean caught;
    private BigDecimal weight;
    private double latG; //Norte - Sur
    private double logG; //Este - Oeste
    private Integer fishId;
    private String name;
    private String site;
    private String firstSighting;
    private String firstLast;
    private String startSeason;
    private String endSeason;
    private String firstLifeWarning;
    private Integer geographieId;
    private String geographieName;
    private String geographieSite;
    private String sightingTime;

}
