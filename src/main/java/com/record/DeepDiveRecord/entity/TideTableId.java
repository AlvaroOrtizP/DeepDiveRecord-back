package com.record.DeepDiveRecord.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class TideTableId implements Serializable {
    private String day;
    private String month;
    private String year;
    private String site;

}
