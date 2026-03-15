package es.upsa.dasi.trabajo1.domain.dtos;

import lombok.*;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@With
public class ContratoDto
{
    private String equipo;
    private String jugador;
    private String finContrato;
}
