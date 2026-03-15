package es.upsa.dasi.trabajo1.msequipos.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.msequipos.application.GetEquipoByIdUseCase;
import es.upsa.dasi.trabajo1.msequipos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetEquipoByIdUseCaseImpl implements GetEquipoByIdUseCase {
    @Inject
    Repository repository;
    @Override
    public Optional<Equipo> execute(String id) throws EquiposAppException {
        return repository.getEquiposById(id);
    }
}
