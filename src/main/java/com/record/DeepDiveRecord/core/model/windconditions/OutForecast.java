package com.record.DeepDiveRecord.core.model.windconditions;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutForecast {
    private List<String> erros;
    public boolean isOk(){
        return this.erros.isEmpty();
    }
}
