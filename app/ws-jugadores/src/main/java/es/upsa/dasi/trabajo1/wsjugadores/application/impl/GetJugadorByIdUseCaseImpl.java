package es.upsa.dasi.trabajo1.wsjugadores.application.impl;


import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.wsjugadores.application.GetJugadorByIdUseCase;
import es.upsa.dasi.trabajo1.wsjugadores.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetJugadorByIdUseCaseImpl implements GetJugadorByIdUseCase {
    @Inject
    Repository repository;
    @Override
    public Optional<Jugador> execute(String id) throws EquiposAppException
    {
        return repository.getJugadorById(id);
    }
}
