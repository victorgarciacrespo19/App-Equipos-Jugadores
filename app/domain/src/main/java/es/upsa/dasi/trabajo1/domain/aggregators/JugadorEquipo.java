package es.upsa.dasi.trabajo1.domain.aggregators;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@With
public class JugadorEquipo
{
    private String fin_contrato;
    private Jugador jugador;
}
