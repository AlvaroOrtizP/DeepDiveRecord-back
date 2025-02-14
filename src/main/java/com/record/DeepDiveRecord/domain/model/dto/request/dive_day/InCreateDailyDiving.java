package com.record.DeepDiveRecord.domain.model.dto.request.dive_day;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InCreateDailyDiving {
    private String day;//dia del año 1 - 365
    private String month;// mes del año 1 - 12
    private String year;// año
    private String beginning;//Hora en la que comienza esa jornada 12:35
    private String end;// Hora en la que termina esa jornada 16:15
    // private String site; //El nombre del pueblo TODO quitar
    // private String name; //El nombre de la zona TODO quitar
    private String notes;// Notas de la jornada
    private Integer assessment;// Valoracion que se le da a la jornada
    // "1" Muy buena
    // "2" Buena
    // "3" Regular
    // "4" Mala
    // "5" Muy mala
    private Integer idGeographicLocation;// Identificador geografico
    private Integer jellyfish;//presencia de medusas
    // "1" Sin medusas
    // "2" Con medusas
    // "3" Muchas medusas
    private Integer visibility;// visibilidad en general siendo
    // "1" Muy buena
    // "2" Buena (8 metros)
    // "3" Mala (4-5 metros)
    // "4" Muy mala (no se ve)
    private Integer seaBackground; //Mar de fondo
    // "1" Sin mar de fondo
    // "2" Un poco de arrastre
    // "3" Arrastre medio
    // "4" Mucho arrastre
    private Integer fishGrass; // Cantidad de peces pasto
    // "1" Poco
    // "2" Medio
    // "3" Mucho
    private Integer presencePlastic;// Presencia de plastico en el agua
    // "1" Nada
    // "2" Poco
    // "3" Mucho


    public static boolean comprobarDatosCreateDiveDay(InCreateDailyDiving input) {
        // Expresión regular para verificar el formato NN:NN donde N es un número
        String regex = "\\d{2}:\\d{2}";

        if (input.getDay() == null || input.getDay().isBlank())
            return false;

        int day = Integer.parseInt(input.getDay());
        if (day > 366 || day < 1)
            return false;

        if (input.getMonth() == null || input.getMonth().isBlank())
            return false;

        int month = Integer.parseInt(input.getMonth());
        if (month > 12 || month < 1)
            return false;

        if (input.getYear() == null || input.getYear().isBlank() || input.getYear().length() != 4)
            return false;

        if (input.getBeginning() == null || input.getBeginning().isBlank() || !input.getBeginning().contains(":")) {
            return false;
        } else {
            //Comprobamos que el valor es numerico
            if (!input.getBeginning().matches(regex)) {
                return false;
            }
            // Eliminar el ':' y verificar si los caracteres restantes son numéricos
            String numericPart = input.getBeginning().replace(":", "");
            if (!numericPart.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }


        if (input.getEnd() == null || input.getEnd().isBlank() || !input.getEnd().contains(":")) {
            return false;
        } else {
            //Comprobamos que el valor es numerico
            if (!input.getEnd().matches(regex)) {
                return false;
            }
            // Eliminar el ':' y verificar si los caracteres restantes son numéricos
            String numericPart = input.getEnd().replace(":", "");
            if (!numericPart.chars().allMatch(Character::isDigit)) {
                return false;
            }
        }


        if (input.getAssessment() == null || input.getAssessment() > 5 || input.getAssessment() < 1)
            return false;

        if (input.getJellyfish() == null || input.getJellyfish() > 3 || input.getJellyfish() < 1)
            return false;

        if (input.getVisibility() == null || input.getVisibility() > 4 || input.getVisibility() < 1)
            return false;

        if (input.getSeaBackground() == null || input.getSeaBackground() > 4 || input.getSeaBackground() < 1)
            return false;

        if (input.getFishGrass() == null || input.getFishGrass() > 3 || input.getFishGrass() < 1)
            return false;

        if (input.getPresencePlastic() == null || input.getPresencePlastic() > 3 || input.getPresencePlastic() < 1)
            return false;
        return true;
    }
}