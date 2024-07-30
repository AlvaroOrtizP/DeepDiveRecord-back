package com.record.DeepDiveRecord.api.domain.diveday.getdivedaybyid;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FishingResponse {
    private Integer id;
    private String notes;
    private boolean caught;
    private double weight;
    private double latG;
    private double longG;
    private String name;
    private String site;
    private String nameFish;
}
