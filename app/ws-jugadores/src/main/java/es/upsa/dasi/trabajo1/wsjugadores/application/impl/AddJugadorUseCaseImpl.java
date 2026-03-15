package es.upsa.dasi.trabajo1.wsjugadores.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.wsjugadores.application.AddJugadorUseCase;
import es.upsa.dasi.trabajo1.wsjugadores.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddJugadorUseCaseImpl implements AddJugadorUseCase {
    @Inject
    Repository repository;
    @Override
    public Jugador execute(Jugador jugador) throws EquiposAppException {
        return repository.saveJugador(jugador.withIdJugador(null));
    }
}
