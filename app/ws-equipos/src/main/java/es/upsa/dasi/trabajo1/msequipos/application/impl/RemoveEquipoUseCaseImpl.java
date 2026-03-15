package es.upsa.dasi.trabajo1.msequipos.application.impl;

import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.msequipos.application.RemoveEquipoUseCase;
import es.upsa.dasi.trabajo1.msequipos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveEquipoUseCaseImpl implements RemoveEquipoUseCase {
    @Inject
    Repository repository;

    @Override
    public void execute(String id) throws EquiposAppException
    {
        repository.removeEquipoById(id);
    }
}
