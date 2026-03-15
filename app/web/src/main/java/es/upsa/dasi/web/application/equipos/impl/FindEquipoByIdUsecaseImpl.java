package es.upsa.dasi.web.application.equipos.impl;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.web.application.equipos.FindEquipoByIdUsecase;
import es.upsa.dasi.web.domain.exceptions.EquipoNotFoundRuntimeException;
import es.upsa.dasi.web.infraestructure.rest.GatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;
@ApplicationScoped
public class FindEquipoByIdUsecaseImpl implements FindEquipoByIdUsecase
{
    @Inject
    @RestClient
    GatewayRestClient restClient;
    @Override
    public Optional<Equipo> execute(String id) {
        try
        {
            Equipo equipo = restClient.findEquipoById(id);
            return Optional.of(equipo);
        } catch (EquipoNotFoundRuntimeException exception)
        {
            return Optional.empty();
        }
    }
}
