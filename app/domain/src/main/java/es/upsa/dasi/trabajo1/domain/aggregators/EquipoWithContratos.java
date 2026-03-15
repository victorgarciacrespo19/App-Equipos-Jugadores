package es.upsa.dasi.trabajo1.domain.aggregators;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@With
public class EquipoWithContratos
{
    private Equipo equipo;
    @Singular("jugador")
    private List<JugadorEquipo> jugadores;
}
