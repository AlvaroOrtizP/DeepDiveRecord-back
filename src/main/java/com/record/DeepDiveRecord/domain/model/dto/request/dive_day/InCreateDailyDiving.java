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
}