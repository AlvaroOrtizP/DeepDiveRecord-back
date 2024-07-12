package com.record.DeepDiveRecord.api.domain.windconditions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutDeepDiveLogger {
    private boolean success;
    private String message; // Mensaje opcional en caso de éxito
    private String error;   // Mensaje opcional en caso de error
}
