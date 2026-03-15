package es.upsa.dasi.trabajo1.aggregator.application;

import es.upsa.dasi.trabajo1.domain.aggregators.EquipoWithContratos;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;

import java.util.Optional;

public interface FindEquipoWithContratosByIdUseCase
{
    Optional<EquipoWithContratos> execute(String id) throws EquiposAppException;
}
