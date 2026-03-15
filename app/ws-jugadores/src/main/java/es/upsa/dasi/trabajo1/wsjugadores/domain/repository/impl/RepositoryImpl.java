package es.upsa.dasi.trabajo1.wsjugadores.domain.repository.impl;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.JugadorNotFoundAppException;
import es.upsa.dasi.trabajo1.wsjugadores.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo1.wsjugadores.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {
    @Inject
    Dao dao;

    @Override
    public List<Jugador> getJugadores() throws EquiposAppException
    {
        List<Jugador> jugadores = dao.findJugadores();
        return jugadores;
    }

    @Override
    public Optional<Jugador> getJugadorById(String id) throws EquiposAppException
    {
        return dao.findJugadorById(id);
    }

    @Override
    public Jugador saveJugador(Jugador jugador) throws EquiposAppException
    {
        return (jugador.getIdJugador() == null)? dao.insertJugador(jugador) : dao.updateJugador(jugador)
                                                                                 .orElseThrow(() -> new JugadorNotFoundAppException());
    }

    @Override
    public void removeJugadorById(String id) throws EquiposAppException
    {
        dao.deleteJugadorById(id);
    }
}
