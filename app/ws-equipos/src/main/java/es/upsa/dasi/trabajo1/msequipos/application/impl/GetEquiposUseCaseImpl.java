package es.upsa.dasi.trabajo1.msequipos.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.msequipos.application.GetEquipoUseCase;
import es.upsa.dasi.trabajo1.msequipos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetEquiposUseCaseImpl implements GetEquipoUseCase {
    @Inject
    Repository repository;


    @Override
    public List<Equipo> execute() throws EquiposAppException {
        List<Equipo> equipos = repository.getEquipos();
        return equipos ;
    }
}
