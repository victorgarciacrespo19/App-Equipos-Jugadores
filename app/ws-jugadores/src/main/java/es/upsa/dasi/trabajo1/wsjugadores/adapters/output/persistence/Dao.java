package es.upsa.dasi.trabajo1.wsjugadores.adapters.output.persistence;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    List<Jugador> findJugadores() throws EquiposAppException;
    Optional<Jugador> findJugadorById(String id) throws EquiposAppException;
    Jugador insertJugador(Jugador jugador) throws EquiposAppException;
    Optional<Jugador> updateJugador(Jugador jugador) throws EquiposAppException;
    void deleteJugadorById(String id) throws EquiposAppException;
}
