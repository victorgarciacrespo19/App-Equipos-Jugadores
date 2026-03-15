package es.upsa.dasi.trabajo1.wsjugadores.application;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.Optional;

public interface GetJugadorByIdUseCase
{
    public Optional<Jugador> execute(String id) throws EquiposAppException;
}
