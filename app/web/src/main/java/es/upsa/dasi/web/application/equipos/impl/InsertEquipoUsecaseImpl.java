package es.upsa.dasi.web.application.equipos.impl;

import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.web.application.equipos.InsertEquipoUsecase;
import es.upsa.dasi.web.infraestructure.rest.GatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
@ApplicationScoped
public class InsertEquipoUsecaseImpl implements InsertEquipoUsecase
{

    @Inject
    @RestClient
    GatewayRestClient restClient;

    @Override
    public Equipo execute(EquipoDto equipoDto) {
        return restClient.insertEquipo(equipoDto);
    }
}
