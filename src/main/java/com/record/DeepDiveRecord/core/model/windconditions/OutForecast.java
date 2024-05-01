package com.record.DeepDiveRecord.core.model.windconditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutForecast {
    private List<String> erros;
    public boolean isOk(){
        return this.erros.isEmpty();
    }
}
