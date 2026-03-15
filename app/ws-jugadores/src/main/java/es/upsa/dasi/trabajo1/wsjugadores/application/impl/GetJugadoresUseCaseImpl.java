package es.upsa.dasi.trabajo1.wsjugadores.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.wsjugadores.application.GetJugadoresUseCase;
import es.upsa.dasi.trabajo1.wsjugadores.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetJugadoresUseCaseImpl implements GetJugadoresUseCase
{
    @Inject
    Repository repository;
    @Override
    public List<Jugador> execute() throws EquiposAppException
    {
        List<Jugador> jugadores = repository.getJugadores();
        return jugadores;
    }
}
