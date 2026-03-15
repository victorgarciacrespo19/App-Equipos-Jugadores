package es.upsa.dasi.web.application.equipos.impl;

import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.web.application.equipos.FindEquiposUsecase;
import es.upsa.dasi.web.infraestructure.rest.GatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
@ApplicationScoped
public class FindEquiposUsecaseImpl implements FindEquiposUsecase
{
    @Inject
    @RestClient
    GatewayRestClient restClient;

    @Override
    public List<Equipo> execute() throws EquiposAppException
    {
        return restClient.findEquipos();
    }
}
