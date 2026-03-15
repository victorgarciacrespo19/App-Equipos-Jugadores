package es.upsa.dasi.trabajo1.wscontratos.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.wscontratos.application.GetContratoByIdUseCase;
import es.upsa.dasi.trabajo1.wscontratos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetContratoByIdUseCaseImpl implements GetContratoByIdUseCase
{
    @Inject
    Repository repository;
    @Override
    public Optional<Contrato> execute(String id) throws EquiposAppException {
        return repository.findById(id);
    }
}
