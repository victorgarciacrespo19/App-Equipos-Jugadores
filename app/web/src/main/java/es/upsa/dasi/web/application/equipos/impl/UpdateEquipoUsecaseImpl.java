package es.upsa.dasi.web.application.equipos.impl;

import es.upsa.dasi.trabajo1.domain.dtos.EquipoDto;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.web.application.equipos.UpdateEquipoUsecase;
import es.upsa.dasi.web.domain.exceptions.EquipoNotFoundRuntimeException;
import es.upsa.dasi.web.infraestructure.rest.GatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;
@ApplicationScoped
public class UpdateEquipoUsecaseImpl implements UpdateEquipoUsecase
{
    @Inject
    @RestClient
    GatewayRestClient restClient;


    @Override
    public Optional<Equipo> execute(String id, EquipoDto equipoDto) {
        try
        {
            Equipo equipo = restClient.updateEquipoById(id, equipoDto);
            return Optional.of(equipo);

        } catch (EquipoNotFoundRuntimeException exception)
        {
            return Optional.empty();
        }

    }
}
