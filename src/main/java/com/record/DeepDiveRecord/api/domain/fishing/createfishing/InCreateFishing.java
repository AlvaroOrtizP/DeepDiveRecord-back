package com.record.DeepDiveRecord.api.domain.fishing.createfishing;

import lombok.*;

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
    private double weight;
    private double latG;
    private double longG;
    private Integer idDiveDay;
}
