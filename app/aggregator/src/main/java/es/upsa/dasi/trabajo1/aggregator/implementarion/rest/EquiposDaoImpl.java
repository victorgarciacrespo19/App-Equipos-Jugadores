package es.upsa.dasi.trabajo1.aggregator.implementarion.rest;

import es.upsa.dasi.trabajo1.aggregator.adapters.output.rest.EquiposDao;
import es.upsa.dasi.trabajo1.aggregator.implementarion.rest.restapi.EquiposRestClient;
import es.upsa.dasi.trabajo1.aggregator.implementarion.rest.restapi.JugadoresRestClient;
import es.upsa.dasi.trabajo1.domain.entities.Equipo;
import es.upsa.dasi.trabajo1.domain.exceptions.EquipoNotFoundAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EquiposDaoImpl implements EquiposDao
{
    @Inject
    @RestClient
    EquiposRestClient restClient;
    @Override
    public Optional<Equipo> findById(String id) throws EquiposAppException {
        try
        {
            return Optional.of( restClient.findById(id) );
        } catch (EquipoNotFoundAppException exception)
        {
            return Optional.empty();
        }
    }
}
