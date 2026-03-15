package es.upsa.dasi.trabajo1.domain.dtos;


import lombok.*;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@With
public class EquipoDto {

    private String nombre;
    private String estadio;
    private String ciudad;
    private String fundacion;
    private double presupuesto;
    private String escudo;
}
