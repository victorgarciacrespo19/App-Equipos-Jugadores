package es.upsa.dasi.web.application.equipos.impl;

import es.upsa.dasi.trabajo1.domain.aggregators.EquipoWithContratos;
import es.upsa.dasi.web.application.equipos.FindEquipoWithContratosByIdUsecase;
import es.upsa.dasi.web.domain.exceptions.EquipoNotFoundRuntimeException;
import es.upsa.dasi.web.infraestructure.rest.GatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;
@ApplicationScoped
public class FindEquipoWithContratosByIdUsecaseImpl implements FindEquipoWithContratosByIdUsecase
{

    @Inject
    @RestClient
    GatewayRestClient restClient;

    @Override
    public Optional<EquipoWithContratos> execute(String id) {
        try
        {
            EquipoWithContratos equipoWithContratos = restClient.findEquipoWithContratos(id);
            return Optional.of(equipoWithContratos);
        } catch (EquipoNotFoundRuntimeException exception)
        {
            return Optional.empty();
        }
    }
}
