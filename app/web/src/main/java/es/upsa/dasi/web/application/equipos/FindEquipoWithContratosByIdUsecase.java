package es.upsa.dasi.web.application.equipos;

import es.upsa.dasi.trabajo1.domain.aggregators.EquipoWithContratos;

import java.util.Optional;

public interface FindEquipoWithContratosByIdUsecase
{
    Optional<EquipoWithContratos> execute(String id);
}
