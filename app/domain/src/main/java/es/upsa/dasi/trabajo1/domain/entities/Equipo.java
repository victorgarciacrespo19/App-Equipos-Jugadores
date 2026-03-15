package es.upsa.dasi.trabajo1.domain.entities;

import lombok.*;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@With
public class Equipo {
    private String id;
    private String nombre;
    private String estadio;
    private String ciudad;
    private String fundacion;
    private double presupuesto;
    private String escudo;
}
