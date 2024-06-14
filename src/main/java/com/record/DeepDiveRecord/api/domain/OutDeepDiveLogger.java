package com.record.DeepDiveRecord.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutDeepDiveLogger {
    private boolean success;
    private String message; // Mensaje opcional en caso de éxito
    private String error;   // Mensaje opcional en caso de error
}
