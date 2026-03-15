package es.upsa.dasi.trabajo1.aggregator.implementarion.rest;

import es.upsa.dasi.trabajo1.aggregator.adapters.output.rest.JugadoresDao;
import es.upsa.dasi.trabajo1.aggregator.implementarion.rest.restapi.JugadoresRestClient;
import es.upsa.dasi.trabajo1.domain.entities.Jugador;
import es.upsa.dasi.trabajo1.domain.exceptions.EquiposAppException;
import es.upsa.dasi.trabajo1.domain.exceptions.JugadorNotFoundAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JugadoresDaoImpl implements JugadoresDao
{
    @Inject
    @RestClient
    JugadoresRestClient restClient;
    @Override
    public List<Jugador> findAll(List<String> ids) throws EquiposAppException {
        return restClient.findAll(ids);
    }

    @Override
    public Optional<Jugador> findById(String id) throws EquiposAppException {
        try
        {
            return Optional.of( restClient.findById(id) );
        } catch (JugadorNotFoundAppException exception)
        {
            return Optional.empty();
        }
    }
}
