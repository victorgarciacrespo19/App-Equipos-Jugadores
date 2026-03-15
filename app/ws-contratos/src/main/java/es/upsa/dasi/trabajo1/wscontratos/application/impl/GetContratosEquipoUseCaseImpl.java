package es.upsa.dasi.trabajo1.wscontratos.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.wscontratos.application.GetContratosEquipoUseCase;
import es.upsa.dasi.trabajo1.wscontratos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetContratosEquipoUseCaseImpl implements GetContratosEquipoUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Contrato> execute(String idEquipo) throws EquiposAppException {
        return repository.findByIdEquipo(idEquipo);
    }
}
