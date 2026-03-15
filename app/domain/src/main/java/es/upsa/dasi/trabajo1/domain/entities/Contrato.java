package es.upsa.dasi.trabajo1.domain.entities;

import lombok.*;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@With
public class Contrato {
    private String id;
    private String equipo;
    private String jugador;
    private String finContrato;

}
