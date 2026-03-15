package es.upsa.dasi.trabajo1.wsjugadores.application;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

public interface AddJugadorUseCase
{
    public Jugador execute(Jugador jugador) throws EquiposAppException;
}
