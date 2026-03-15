package es.upsa.dasi.web.application.equipos.impl;

import es.upsa.dasi.web.application.equipos.DeleteEquipoById;
import es.upsa.dasi.web.infraestructure.rest.GatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
@ApplicationScoped
public class DeleteEquipoByIdImpl implements DeleteEquipoById
{
    @Inject
    @RestClient
    GatewayRestClient gatewayRestClient;
    @Override
    public void execute(String id)
    {
        gatewayRestClient.deleteEquipoById(id);
    }
}
