package es.upsa.dasi.trabajo1.wsjugadores.domain.repository;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Jugador> getJugadores() throws EquiposAppException;
    Optional<Jugador> getJugadorById(String id) throws EquiposAppException;
    Jugador saveJugador(Jugador jugador) throws EquiposAppException;
    void removeJugadorById(String id) throws EquiposAppException;
}
