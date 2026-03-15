package es.upsa.dasi.trabajo1.wsjugadores.application;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;

public interface GetJugadoresUseCase
{
    List<Jugador> execute() throws EquiposAppException;
}
