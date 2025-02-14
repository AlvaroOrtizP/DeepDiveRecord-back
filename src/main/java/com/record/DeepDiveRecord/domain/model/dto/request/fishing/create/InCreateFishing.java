package com.record.DeepDiveRecord.domain.model.dto.request.fishing.create;

import com.record.DeepDiveRecord.domain.model.util.Validator;
import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal weight;
    private Double latG;
    private Double longG;
    private Integer idDiveDay;
    private String sightingTime;


    public final static boolean comprobarDatosInCreateFishing(InCreateFishing input) {
        // Expresión regular para verificar el formato NN:NN donde N es un número
        if (Validator.validateInteger(input.getFishId())) {
            return false;
        }
        if (Validator.validateString(input.getName())) {
            return false;
        }
        if (Validator.validateString(input.getSite())) {
            return false;
        }
        if (Validator.validateString(input.getNotes())) {
            return false;
        }
        if (Validator.validateBigDecimal(input.getWeight())) {
            return false;
        }
        if (Validator.validateDouble(input.getLatG())) {
            return false;
        }
        if (Validator.validateDouble(input.getLongG())) {
            return false;
        }
        if (Validator.validateInteger(input.getIdDiveDay())) {
            return false;
        }
        if (Validator.validateHora(input.getSightingTime())) {
            return false;
        }
        return true;
    }
}