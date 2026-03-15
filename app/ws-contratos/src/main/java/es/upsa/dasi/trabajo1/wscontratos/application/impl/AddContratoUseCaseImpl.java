package es.upsa.dasi.trabajo1.wscontratos.application.impl;

import es.upsa.dasi.trabajo1.domain.entities.Contrato;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.wscontratos.application.AddContratoUseCase;
import es.upsa.dasi.trabajo1.wscontratos.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddContratoUseCaseImpl implements AddContratoUseCase
{
    @Inject
    Repository repository;
    @Override
    public Contrato execute(Contrato contrato) throws EquiposAppException {
        return repository.save(contrato);
    }
}
