package es.upsa.dasi.trabajo1.msequipos.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.msequipos.application.AddEquipoUseCase;
import es.upsa.dasi.trabajo1.msequipos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddEquipoUseCaseImpl implements AddEquipoUseCase
{
    @Inject
    Repository repository;
    @Override
    public Equipo execute(Equipo equipo) throws EquiposAppException {
        return repository.saveEquipo(equipo) ;
    }
}
